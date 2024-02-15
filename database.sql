Create database tokonyadia_db;
use tokonyadia_db;

create table m_product(
	id varchar(100) not null,
    name varchar(100) not null,
    price int not null,
    stock int not null,
    primary key (id)
)engine = InnoDB;
desc m_product;
show tables;

select * from m_product;