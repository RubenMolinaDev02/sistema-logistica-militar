# ARMORY LOGISTICS SYSTEM

## Manual de Administrador y Usuario

Url de la app: https://sistema-logistica-militar.netlify.app/

---

# Índice

1. Gestión de Catálogo
2. Gestión de Localizaciones
3. Administración de Usuarios
4. Manual de Usuario
5. Referencias Únicas
6. Notas Importantes

---

# Gestión de Catálogo

## Vista General

El módulo **Catálogo** permite administrar los recursos militares almacenados en el sistema.

Los usuarios autorizados pueden:

* Consultar el inventario disponible.
* Buscar elementos mediante filtros.
* Crear nuevos registros.
* Visualizar información detallada.
* Editar registros existentes.

---

## Listado de Elementos

La pantalla principal muestra todos los elementos registrados organizados por categorías.

### Características

* Búsqueda por nombre.
* Búsqueda por referencia.
* Filtrado por categoría.
* Navegación paginada.
* Acceso rápido a la ficha detallada de cada elemento.

### Categorías Disponibles

* Armas
* Calibres
* Munición
* Cargadores
* Cascos
* Chalecos
* Placas balísticas
* Visión nocturna
* Uniformidad
* Miras
* Pistolas
* Granadas
* Filtros NBQ
* Máscaras de gas
* Bayonetas
* Accesorios
* Plataformas de armas
* Culatas
* Guardamanos
* Misc

---

## Crear Elemento

Permite registrar nuevos activos en el inventario.

<img width="1919" height="957" alt="2" src="https://github.com/user-attachments/assets/9e55c883-8875-407a-a09c-50269db438b6" />


### Componentes

Dependiendo del tipo de elemento pueden registrarse:

* Plataforma
* Calibre
* Culata
* Guardamanos

---

## Detalle del Elemento

La vista de detalle muestra toda la información asociada al activo seleccionado.

<img width="1915" height="953" alt="3" src="https://github.com/user-attachments/assets/f6ae690a-a1aa-410f-a406-63551e822240" />


---

## Edición de Elementos

Los registros existentes pueden modificarse mediante la opción **Edit**.

<img width="1921" height="957" alt="4" src="https://github.com/user-attachments/assets/2178ff18-5361-42a9-bcf5-7313421fdb35" />


---

# Gestión de Localizaciones

## Vista General

El módulo **Localizaciones** permite administrar bases, almacenes y otras instalaciones logísticas.

---

## Listado de Localizaciones

La vista principal muestra todas las localizaciones registradas en el sistema.

<img width="1920" height="960" alt="5" src="https://github.com/user-attachments/assets/a19bfdc2-01b2-4465-ab4e-ff1546ef4160" />


### Funcionalidades

* Búsqueda por nombre.
* Creación de nuevas localizaciones.
* Consulta detallada de cada instalación.

### Tipos de Localización

* Base Militar
* Almacén
* Centro Logístico
* Instalación Operativa

---

## Crear Localización

Permite registrar una nueva instalación logística.

<img width="1921" height="952" alt="6" src="https://github.com/user-attachments/assets/9540185a-3fd3-43f1-83ec-ad421c56931c" />

---

## Detalle de Localización

La vista de detalle muestra información completa sobre una instalación.

<img width="1917" height="957" alt="7" src="https://github.com/user-attachments/assets/094de028-462e-44ba-b51a-bd44737e2d05" />


### Información General

* Nombre
* Referencia única
* País

### Clasificación

* Tipo de localización
* Estado operativo

### Capacidad

* Número máximo de tropas soportadas

---

# Administración de Usuarios

## 1. Acceder al módulo de usuarios

Desde el menú lateral seleccione **Usuarios** para consultar, buscar y gestionar cuentas.

<img width="1919" height="956" alt="9" src="https://github.com/user-attachments/assets/57cc0ce7-223e-4825-8ce3-776db3c59cd3" />


---

## 2. Consultar detalles de un usuario

Seleccione un usuario para acceder a su información personal, militar y de contacto.

<img width="1917" height="959" alt="10" src="https://github.com/user-attachments/assets/37e3fdf9-d921-4212-9f0b-44bdfe0abcf8" />


---

## 3. Crear usuario

Permite dar de alta un usuario en el sistema.

<img width="1921" height="957" alt="17" src="https://github.com/user-attachments/assets/73b62de9-9b93-45af-8e96-da67348b87de" />


Una vez creado el usuario aparecera la contraseña temporal, es importante guardarla.

### Importante

* La contraseña temporal debe ser guardada por el administrador.
* Si se pierde, será necesario generar una nueva.
* El usuario no podrá acceder sin sus credenciales.

---

## 4. Asignar roles obligatorios

Acceda a la pestaña **Role Mapping**.

<img width="1916" height="956" alt="12" src="https://github.com/user-attachments/assets/5f8c51c4-ac42-4c93-a8de-a1a5d388dc05" />

<img width="1916" height="958" alt="13" src="https://github.com/user-attachments/assets/ca706453-6b6a-4f76-9bc0-334d29a88d8a" />


Pulse **Assign Role**.

Seleccione el rol correspondiente:

* `soldier`
* `inventory-manager`
* `system-admin`
* `public-access`

⚠️ Si el usuario no tiene roles asignados, no podrá utilizar la aplicación.

---

## 5. Entrega de credenciales

Una vez:

* Creada la cuenta.
* Asignada la contraseña temporal.
* Asignados los roles.

El administrador deberá facilitar al usuario:

* Nombre de usuario.
* Contraseña temporal.

para que pueda iniciar sesión.

---

# Manual de Usuario

## Mi Perfil

Desde **Mi Perfil** el usuario puede consultar:

* Información personal.
* Información militar.
* Datos de contacto.

También dispone de:

* Editar perfil.
* Cambiar contraseña.

<img width="1924" height="948" alt="14" src="https://github.com/user-attachments/assets/bc928bdd-50c9-4291-a1fc-a2b19b1905b3" />


---

## Editar Perfil

El usuario puede actualizar:

* Email.
* Nombre.
* Apellidos.
* Teléfono.
* Imagen de perfil.

Los cambios se guardan mediante el botón **Save**.

<img width="1920" height="957" alt="16" src="https://github.com/user-attachments/assets/b2337ce3-9213-46aa-b977-0f957fde9ae7" />


---

## Cambiar Contraseña

Desde **Mi Perfil**, pulse **Change Password**.

La aplicación abrirá automáticamente la consola de cuenta de Keycloak.

<img width="1925" height="960" alt="15" src="https://github.com/user-attachments/assets/e81d377b-299a-47de-8aca-7ee746f8308b" />


Pulse **Update** en el apartado de contraseña.

Introduzca:

1. Contraseña actual.
2. Nueva contraseña.
3. Confirmación de la nueva contraseña.

Finalmente confirme los cambios.

---

# Referencias Únicas

Cada entidad del sistema genera automáticamente un identificador único.

## Ejemplos

### Armas

WEAP-20260609-22JX22

### Localizaciones

LCTN-20260609-NL6GEX

Estas referencias permiten identificar de forma inequívoca cada elemento registrado dentro del sistema.

---

# Notas Importantes

* Los roles se gestionan exclusivamente desde Keycloak.
* Los administradores deben conservar temporalmente las contraseñas iniciales hasta entregarlas al usuario.
* Sin roles asignados el acceso a la aplicación será denegado.
* Se recomienda que el usuario cambie la contraseña en su primer acceso.
* Todas las entidades del sistema disponen de identificadores únicos generados automáticamente.
* Los cambios realizados en catálogos, localizaciones y usuarios quedan sujetos a los permisos asignados mediante roles.
