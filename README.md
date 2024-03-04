Demo: First Run:

Fetching data from the database and saving it to the Redis cache:

http://localhost:8080/products/3 

Time: 180ms 

![image](https://github.com/duongne1/KTTKPM_week4/assets/90126154/71196082-0551-439b-a4db-af74e8e5e574) 

Data save cash:

![image](https://github.com/duongne1/KTTKPM_week4/assets/90126154/c6b86c22-76f6-4e5d-afd1-89c30a5e5af9) 

Second Run: Fetching data from cache => Time query reduce:

http://localhost:8080/products/3 

Time: 5ms 

![image](https://github.com/duongne1/KTTKPM_week4/assets/90126154/cfec1413-9105-425a-97d7-b6e693441279)
