FROM postgres:13.1-alpine

ADD init.sql /docker-entrypoint-initdb.d/init.sql