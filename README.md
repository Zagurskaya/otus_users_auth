# USER-AUTH-PROJECT

##    Backend for frontends. Apigateway

#### Endpoint curl http://arch.homework/

- Развертывание из каталога \otus_users_auth\helm\. Манифесты развертываются в namespace user

      helm -n user upgrade --install --create-namespace user .
- Удаление развернутых ресурсов

      helm -n user delete user
      kubectl delete namespace user



