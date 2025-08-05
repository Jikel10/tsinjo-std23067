CREATE DATABASE tsinjo_prod;

CREATE USER prod_user WITH PASSWORD 'prod_password';

GRANT ALL PRIVILEGES ON DATABASE tsinjo_prod TO prod_user;

CREATE DATABASE tsinjo_preprod;

CREATE USER preprod_user WITH PASSWORD 'preprod_password';

GRANT ALL PRIVILEGES ON DATABASE tsinjo_preprod TO preprod_user;
