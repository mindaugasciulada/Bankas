# Project Title

## How to run

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installing

What things you need to install the software and how to install them

```
docker build -t webservisai/webservisai:first .

docker run -d -p 80:4567 webservisai/webservisai:first
```

### Accessibility


```
localhost:4567/
internet: 193.219.12.42
```


###Get
```
/user - all users

/user:id - get user by id
```

###Post
```
/user - create user
```

###Put
```
/user - update user
```

###Delete
```
/user:id - delete user
```


### Example

```
{
    "id": 4,
    "name": "Tomas",
    "lastName": "Akvaliauskas",
    "balance": 1515
}
```


## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds


## Authors

* **Mindaugas** - *Initial work* - [PurpleBooth](https://github.com/mindaugasciulada/Bankas)