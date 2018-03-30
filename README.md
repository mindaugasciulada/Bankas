# Bankas

## How to run

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


### Get
```
/user - all users

/user:id - get user by id
```

### Post
```
/user - create user
```

### Put
```
/user - update user
```

### Delete
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

* [Spark](http://sparkjava.com/) - micro framework for creating web applications
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Mindaugas** - *Initial work* - [Bankas](https://github.com/mindaugasciulada/Bankas)