# tekton-percentage-api
Micro servicio para el manejo de porcentaje

# Descripcion

API que suma dos números y aplica un porcentaje dinamico proveido de un servicio exterior, con un sistema de respaldo de media hora en caso que la comunicación exterior falle y un posterior guardado de información del historico en BD.

## Recursos
- POST /api/v1/calculate : { num1, num2 } => sum + percentage
  - curl --location 'http://localhost:8080/tekton/api/v1/percentage' \
    --header 'Content-Type: application/json' \
    --data '{
    "firstNumber": 50,
    "secondNumber": 50
    }'
- GET /api/v1/history : Llamado a todo el historial:
  - curl --location 'http://localhost:8080/tekton/api/v1/history'
- Swagger UI: http://localhost:8080/tekton/swagger-ui/index.html
- Prometheus metrics: http://localhost:8080/tekton/actuator/prometheus
- Api: http://localhost:8080

## Guia
- Abrir terminal y ejecutar:
  - git clone https://github.com/cristian98o2/tekton-percentage-api.git
- En la rama main ejecutar:
  - ./gradlew clean build -x test
  - docker build -t tekton/tekton-percentage-api:latest .
  - docker-compose up --build
- Abrir postman e importar el archivo Tekton Percentage.portman_collection.json
- Para ver la base de datos se pueden conectar con las siguientes credenciales:
  - Host: localhost
  - Port: 5432
  - Database: percentagedb
  - User: postgres
  - Password: postgres
