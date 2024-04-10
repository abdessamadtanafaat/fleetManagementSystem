
to work with cache redis : 
install redis Insight.
configure it . 
add this lines in the applicatio properties :

spring.security.filter.order=10
logging.level.org.springframework.data.redis=DEBUG
spring.main.allow-bean-definition-overriding=true

spring.data.redis.host=localhost
spring.data.redis.port=6379


add this lines in the application properties : 
#$ git update-index --skip-worktree src/main/resources/application.properties
