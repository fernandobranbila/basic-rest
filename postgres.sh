#!/bin/bash


docker run --name basic-rest --rm -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=basic-rest -p 5432:5432 -d postgres
