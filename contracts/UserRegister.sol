pragma solidity ^0.4.18;


contract UserRegister {
    struct User {
        string userName;
        string avatarUrl;
        bool isValid;
    }

    mapping (address => User) public users;

    event UserRegistered(User indexed newUser);


    modifier onlyRegistered() {
        require(isRegistered(msg.sender));
        _;
    }


    modifier onlyNotRegistered() {
        require(!isRegistered(msg.sender));
        _;
    }

    function isRegistered(address signer) public view returns (bool) {
        return users[signer].isValid;
    }


    function registerUser(string userName, string avatarUrl) public onlyNotRegistered {
        address newUserAddr = msg.sender;
        require(newUserAddr != address(0));

        User memory newUser = User(userName, avatarUrl, true);
        
        UserRegistered(newUser);
        users[newUserAddr] = newUser;
    }

}