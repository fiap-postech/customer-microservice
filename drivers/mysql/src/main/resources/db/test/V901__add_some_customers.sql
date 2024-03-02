insert into customer (id, uuid, document, name, email, enabled, created, last_updated, version)
values (id, UUID(), '76633036876', 'Luna Pietra Heloisa Drumond', 'luna_pietra_drumond@cppcoder.com', 1, now(), now(), 0),
       (id, UUID(), '65511960802', 'Camila Louise da Cunha', 'camila.louise.dacunha@homail.com', 1, now(), now(), 0),
       (id, UUID(), '89093796852', 'Pietro Erick dos Santos', 'pietro_erick_dossantos@afsn.com.br', 1, now(), now(), 0),
       (id, UUID(), '65881292383', 'Isabelle Antônia Oliveira', 'isabelle_oliveira@fcacomputers.com.br', 1, now(), now(), 0),
       (id, UUID(), '32495281885', 'Juan Luan Danilo Fernandes', 'juan.luan.fernandes@click21.com.br', 1, now(), now(), 0);

insert into customer_data_removal
values (1, 'c1dc90c3-3e11-4d92-afc6-1702f23e9906', (select id from customer where document = '65881292383'), 'PENDING', now(), now(), now(), 0);

insert into customer_data_removal_item
values (1, uuid(), 'purchase-service', 'PENDING', now()),
       (1, uuid(), 'customer-service', 'PENDING', now());