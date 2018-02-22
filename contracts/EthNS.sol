pragma solidity ^0.4.19;

import "github.com/Arachnid/solidity-stringutils/strings.sol";

contract EthNSDomain {
    using strings for *;
    
    string public selfDomain;
    
    mapping (string => address) subDomains;
    
    mapping (string => bytes32) names;
    
    event SubDomainUpdated(string indexed subDomain, address indexed subDomainAddress);
    
    event NameUpdated(string indexed name, bytes32 indexed value);
    
    function EthNSDomain(string subDomain) public {
        selfDomain = subDomain;
    }
    
    function updateSubdomain(string subDomain, address subDomainAddress) public {
        SubDomainUpdated(subDomain, subDomainAddress);
        subDomains[subDomain] = EthNSDomain(subDomainAddress);
    }
    
    function updateName(string name, bytes32 value) public {
        NameUpdated(name, value);
        names[name] = value;
    }
    
    function query(string name) public view returns (bytes32) {
        var nameSplit = name.toSlice();
        var domainSplit = nameSplit.rsplit(".".toSlice());
        
        var domain = domainSplit.toString();
        
        if(nameSplit.empty()){
            return names[domain];
        } else {
            return EthNSDomain(subDomains[domain]).query(nameSplit.toString());
        }
        
    }
    
    
    
    
}