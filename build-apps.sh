cd discovery && ./mvnw clean install
cd ../ordersms && ./mvnw clean install
cd ../productsms && ./mvnw clean install
cd ../paymentsms && ./mvnw clean install
cd ../gateway && ./mvnw clean install

#cd .. && docker-compose up --build
docker-compose up --build