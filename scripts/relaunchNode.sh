#!/usr/bin/env bash

docker run -d -p 8545:8545 -v $PWD/ganacheDB:/db dreigada/ganache-cli:latest -a 1 -u 0 --db /db


