declare global {
  interface Window {
    __config: any;
  }
}

const config = window.__config || {};

export const environment = {
  production: false,

  keycloak: {
    url:
      config.keycloak?.url ??
      'http://localhost:9090',

    realm:
      config.keycloak?.realm ??
      'armory-realm',

    clientId:
      config.keycloak?.clientId ??
      'weapon-service',

    consoleUrl:
      config.keycloak?.consoleUrl ??
      'http://localhost:9090/admin/armory-realm/console/#/armory-realm/users'
  },

  apis: {
    information:
      config.apis?.information ??
      'http://localhost:8080',

    inventory:
      config.apis?.inventory ??
      'http://localhost:8080',

    location:
      config.apis?.location ??
      'http://localhost:8080'
  },

  roles: {
    admin:
      config.roles?.admin ??
      'system-admin',

    manager:
      config.roles?.manager ??
      'inventory-manager',

    soldier:
      config.roles?.soldier ??
      'soldier',

    publicAccess:
      config.roles?.publicAccess ??
      'public-access'
  }
};