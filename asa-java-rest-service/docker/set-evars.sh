#!/bin/sh
# ----------------------------------------------------------------------------
unset PG_CONTAINER_NAME
unset PG_USER
unset PG_PASSWORD
unset PG_DB
unset PG_DATA

export PG_CONTAINER_NAME='postgres_demo'
export PG_USER='demo'
export PG_PASSWORD='admindemo'
export PG_DB='demo'
export PG_DATA='~/data/postgres-demo'