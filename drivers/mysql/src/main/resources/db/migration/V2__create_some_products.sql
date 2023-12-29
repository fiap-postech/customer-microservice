insert into product (id, uuid, name, description, price, image, category, enabled, created, last_updated, version)
values (id, uuid(), 'X Tudo', 'Um belo X Tudão', 23.50, 'http://localhost:8888/lanche.png', 'SANDWICH', 1, now(), now(), 0),
       (id, uuid(), 'Bolo', 'Um bolo irresitível', 8.50, 'http://localhost:8888/sobremesa.png', 'DESSERT', 1, now(), now(), 0),
       (id, uuid(), 'Bata Frita', 'Uma bela porção de batata sequinha', 6.00, 'http://localhost:8888/fritas.png', 'SIDE_DISH', 1, now(), now(), 0),
       (id, uuid(), 'Coca Cola', 'Delícia', 7.50, 'http://localhost:8888/refrigerante.png', 'BEVERAGE', 1, now(), now(), 0),
       (id, uuid(), 'Combo Tudo', 'Lanche + Batata + Refri', 31.50, 'http://localhost:8888/combo.png', 'COMBO', 1, now(), now(), 0);

insert into combo(id, sandwich_id, beverage_id, side_dish_id)
select (select id from product where category = 'COMBO') as id,
       (select id from product where category = 'SANDWICH') as sandwich_id,
       (select id from product where category = 'BEVERAGE') as beverage_id,
       (select id from product where category = 'SIDE_DISH') as side_dish_id;


