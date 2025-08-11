# JTable - Gestor de Personas

Este proyecto es una aplicación Java desarrollada con **Swing** que permite gestionar personas a través de una interfaz gráfica.  
El usuario puede **agregar**, **editar**, **eliminar** y **visualizar** los registros en una **tabla interactiva** (`JTable`).

---

## 📌 Características

- **Agregar personas** con validación de datos.
- **Actualizar registros** existentes.
- **Eliminar personas** seleccionadas.
- **Visualización en tiempo real** de la información en una `JTable`.
- **Conexión a base de datos MySQL** mediante `mysql-connector-j`.
- **Patrón de diseño MVC** para una mejor organización del código.

---

## 🛠️ Tecnologías utilizadas

- **Java 24+** (compatible con versiones posteriores)
- **Swing** (Interfaz gráfica)
- **MySQL** (Base de datos)
- **MySQL Connector/J** (`mysql-connector-j-9.4.0.jar`)
- **IntelliJ IDEA**

---

## ⚙️ Configuración del entorno

1. **Clonar el repositorio** o descargar el proyecto.
2. **Importar en IntelliJ IDEA o Eclipse** como proyecto Java.
3. **Agregar la librería MySQL Connector/J**  
   - Ubicación: `mysql-connector-j-9.4.0.jar`
4. **Configurar la base de datos** en `archivo.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/personmanager_db
   db.user=usuario
   db.password=contraseña
   ```

---

## 📊 Crear Base de Datos

``` SQL
CREATE DATABASE personmanager_db;

CREATE TABLE empleado (
    documento VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL
);
```

## By Miguel Ángel Vargas Navarro - June 11 2025



