package br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.repository;

import br.com.fiap.tech.challenge.adapter.dto.DataRemovalInquiryDTO;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalInquiryRepository;
import br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.dto.FileContentDTO;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.config.EnvironmentProperties.CUSTOMER_DATA_REMOVAL_AES_ALGORITHM;
import static br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.config.EnvironmentProperties.CUSTOMER_DATA_REMOVAL_AES_IV;
import static br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.config.EnvironmentProperties.CUSTOMER_DATA_REMOVAL_AES_PASSWORD;
import static br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.config.EnvironmentProperties.CUSTOMER_DATA_REMOVAL_BUCKET_NAME;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.UNKNOWN_ERROR;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class DataRemovalInquiryRepositoryImpl implements DataRemovalInquiryRepository {

    private final ObjectMapper mapper;

    private final S3Template s3;

    @Value("${" + CUSTOMER_DATA_REMOVAL_BUCKET_NAME + "}")
    private String bucketName;

    @Value("${" + CUSTOMER_DATA_REMOVAL_AES_ALGORITHM + "}")
    private String algorithm;

    @Value("${" + CUSTOMER_DATA_REMOVAL_AES_PASSWORD + "}")
    private String password;

    @Value("${" + CUSTOMER_DATA_REMOVAL_AES_IV + "}")
    private String iv;

    @Override
    public void write(DataRemovalInquiryDTO dto) {
        try {
            var encryptedContent = encrypt(mapper.writeValueAsString(dto));

            s3.store(
                    bucketName,
                    String.format("%s.json", dto.getDataRemovalId()),
                    new FileContentDTO(encryptedContent)
            );
        } catch (Exception e) {
            throw new ApplicationException(UNKNOWN_ERROR, e.getMessage());
        }
    }

    private SecretKey getKeyFromPassword() {
        return new SecretKeySpec(password.getBytes(UTF_8), "AES");
    }

    @SuppressWarnings("java:S3329")
    public IvParameterSpec generateIv() {
        return new IvParameterSpec(iv.getBytes(UTF_8));
    }

    public String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        var cipher = Cipher.getInstance(algorithm);

        cipher.init(Cipher.ENCRYPT_MODE, getKeyFromPassword(), generateIv());

        var cipherText = cipher.doFinal(input.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }
}
