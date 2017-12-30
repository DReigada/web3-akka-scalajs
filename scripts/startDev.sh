#!/usr/bin/env bash

mkdir -p ganacheDB
ganacheHash=$(docker run -d -p 8545:8545 -v $PWD/ganacheDB:/db dreigada/ganache-cli:latest -a 2 -u 0 -u 1 --db /db)

docker run -d -p 8080:8080 dreigada/browser-solidity:latest

sleep 4

docker logs $ganacheHash | head -17 | tail -15 

remixd -S $1
