# Bankas
## How to run
'docker build -t webservisai/webservisai:first .''
'docker run -d -p 80:4567 webservisai/webservisai:first'

localhost:4567
išorėje: 193.219.12.42

# Methods

##Get 
'/user - all users
'
'/user:id - get user by id
'

##Post
'/user - create user
'

##Put
'
/user - update user
'
##Delete
'
/user:id - delete user
'
#Example info

'
{
    "id": 4,
    "name": "Tomas",
    "lastName": "Akvaliauskas",
    "balance": 1515
}
'