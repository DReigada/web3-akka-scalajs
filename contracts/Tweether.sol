pragma solidity ^0.4.19;

import './UserRegister.sol';

contract Tweether is UserRegister {

    struct Tweeth {
        UserRegister.User user;
        string content;
        uint timestamp;
    }
    
    Tweeth[] tweeths;
    
    event NewTweeth(Tweeth indexed tweeth);

    
    function makeTweeth(string content) public onlyRegistered {
        User memory user = users[msg.sender];
        
        Tweeth memory tweeth = Tweeth(user, content, now);
        
        NewTweeth(tweeth);
        tweeths.push(tweeth);
    }
    
    // username, avatar, address, content, timestamp
    function getTweeth(uint index) public view returns (string, string, address, string, uint) {
        require(index < tweeths.length);
        Tweeth memory t = tweeths[index];
        return (t.user.userName, t.user.avatarUrl, t.user.userAddress, t.content, t.timestamp);
    }
    
    function getNumberOfTweeths() public view returns (uint) {
        return tweeths.length;
    }
    
}
