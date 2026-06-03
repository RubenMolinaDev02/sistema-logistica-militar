window.__config = {
  keycloak: {
    url: 'https://auth.midominio.com',
    realm: 'armory-realm',
    clientId: 'weapon-service',
    consoleUrl: 'https://auth.midominio.com/admin/armory-realm/console/#/armory-realm/users'
  },

  apis: {
    information: 'https://api.midominio.com',
    inventory: 'https://api.midominio.com',
    location: 'https://api.midominio.com'
  },

  roles: {
    admin: 'system-admin',
    manager: 'inventory-manager',
    soldier: 'soldier',
    publicAccess: 'public-access'
  }
};