# Gestión de Carrito de Compras API

## Descripción

API RESTful para gestionar un carrito de compras. Realiza operaciones CRUD utilizando Redis como base de datos.

## Endpoints

- `POST /carrito`: Crear un nuevo carrito.
- `GET /carrito/<carrito_id>`: Obtener un carrito por ID.
- `PUT /carrito/<carrito_id>`: Actualizar un carrito existente.
- `DELETE /carrito/<carrito_id>`: Eliminar un carrito.

## Requisitos

- Python 3.x
- Redis

## Instalación

1. Clonar el repositorio.
2. Instalar dependencias:

```bash
pip install -r requirements.txt
```

3. Ejecutar la aplicación:

```bash
python app/main.py
```

### Instrucciones de uso

1. **Instalar Redis**: Asegúrate de tener Redis instalado y en ejecución en tu máquina local (puedes usar Docker o instalar Redis de forma nativa).
2. **Ejecutar la API**: En la raíz del proyecto, ejecuta el archivo `main.py` para levantar el servidor Flask.
3. **Probar los endpoints**: Puedes usar herramientas como Postman o cURL para probar los diferentes endpoints.

¿Necesitas alguna explicación adicional sobre alguna parte del código o configuración?