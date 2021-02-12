insert into category(id, name)
values (1, 'Категория-1');
insert into category(id, name)
values (2, 'Категория-2');
insert into category(id, name)
values (3, 'Категория-3');
insert into category(id, name)
values (4, 'Категория-4');

insert into news(id, name, text, date, category_id)
values (1, 'Заголовок-1', 'Текст-1', '2021-02-11 11:00:00', 1);
insert into news(id, name, text, date, category_id)
values (2, 'Заголовок-2', 'Текст-2', '2021-02-11 13:00:00', 2);
insert into news(id, name, text, date, category_id)
values (3, 'Заголовок-3', 'Текст-3', '2021-02-11 11:30:15', 3);
insert into news(id, name, text, date, category_id)
values (4, 'Заголовок-4', 'Текст-4', '2021-02-11 09:15:24', 4);

alter sequence category_id_seq restart with 5;
alter sequence news_id_seq restart with 5;