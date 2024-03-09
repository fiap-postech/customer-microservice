package br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String CUSTOMER_DATA_REMOVAL_BUCKET_NAME = "aws.resources.s3.customer.data.removal";
    public static final String CUSTOMER_DATA_REMOVAL_AES_ALGORITHM = "customer.data.removal.aes.algorithm";
    public static final String CUSTOMER_DATA_REMOVAL_AES_PASSWORD = "customer.data.removal.aes.password";
    public static final String CUSTOMER_DATA_REMOVAL_AES_IV = "customer.data.removal.aes.iv";

}
