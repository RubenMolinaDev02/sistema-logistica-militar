export const environment = {
  production: false,
  keycloak: {
    url: 'http://localhost:9090',
    realm: 'armory-realm',
    clientId: 'weapon-service'
  },
  apis: {
    information: 'http://localhost:8080',
    inventory:   'http://localhost:8080',
    location:    'http://localhost:8080'
  }
};
