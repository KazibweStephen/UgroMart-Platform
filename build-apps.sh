cd gateway && ./mvnw clean install
cd ../ordersms && ./mvnw clean install
cd ../productsms && ./mvnw clean install

cd .. && docker-compose up -d