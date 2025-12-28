# 📱 Gimnasio FitCampus – App Android Responsive & Adaptive

Aplicación Android desarrollada en **Kotlin** con **Jetpack Compose**, siguiendo el patrón **MVVM (Model–View–ViewModel)**. El objetivo del proyecto es demostrar el uso de **diseño responsive y adaptative**, cumpliendo los requisitos del módulo **DAM2 – Desenvolupament d’interfícies**.

La app simula el registro y login de usuarios para un servicio ficticio de gimnasio llamado **FitCampus**.

---

## 🎯 Objetivos del proyecto

* Aplicar diseño **responsive** (adaptación a orientación vertical/horizontal).
* Aplicar diseño **adaptive** (layouts distintos según tamaño de pantalla).
* Implementar arquitectura **MVVM**.
* Uso de **Jetpack Compose**, **LiveData** y **Navigation (NavHost)**.
* Validación completa de formularios.

---

## 🧱 Arquitectura

El proyecto sigue el patrón **MVVM**:

### 📦 Model

```kotlin
data class User(
    val username: String,
    val password: String
)
```

Representa el modelo de usuario almacenado en memoria.

### 👁️ View

* Pantallas construidas con **Jetpack Compose**.
* Composables adaptados a:

  * **Compact**
  * **Medium**
  * **Expanded**
* Uso de `WindowSizeClass` y `WindowWidthSizeClass`.
* Banner superior reutilizable (`AppBanner`).

Pantallas principales:

* `RegisterScreen`
* `RegisterSuccessScreen`
* `LoginScreen`

### 🧠 ViewModel

* `AuthViewModel`
* Gestión de estado con **LiveData**.
* Validaciones de campos.
* Lógica de registro y login.

---

## 📐 Diseño Responsive & Adaptive

### 🔹 Adaptive

Se implementan **tres diseños distintos** según el tamaño de pantalla:

| Tamaño   | Comportamiento                                         |
| -------- | ------------------------------------------------------ |
| Compact  | Layout en columna, contenido simplificado              |
| Medium   | Layout dividido, uso de Cards                          |
| Expanded | Layout en múltiples columnas con información adicional |

Controlado mediante:

```kotlin
windowSize.widthSizeClass
```

### 🔹 Responsive

* Adaptación automática al cambiar orientación **portrait / landscape**.
* Ajuste dinámico de:

  * Alturas
  * Tamaños de texto
  * Tamaños de iconos

Ejemplo:

```kotlin
val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
```

---

## 🖼️ Banner superior

El componente `AppBanner`:

* Gradiente horizontal
* Logo del gimnasio
* Título y subtítulo
* Tamaño dinámico según pantalla y orientación

Cumple el requisito de **logo + lema** del servicio.

---

## 📝 Registro de usuario

Datos solicitados:

* Nombre completo
* Fecha de nacimiento
* Email
* Teléfono
* Nombre de usuario
* Password
* Confirmación de password
* Aceptación de términos

### ✔ Validaciones

* Nombre: solo letras
* Fecha: formato `dd/mm/yyyy`
* Email: debe contener `@`
* Teléfono: solo números (9–15 dígitos)
* Usuario: mínimo 3 caracteres y único
* Password: mínimo 6 caracteres
* Confirmación: debe coincidir
* Términos: obligatorios

Los errores se muestran en tiempo real debajo de cada campo.

---

## 🔐 Login

* Verificación de credenciales contra usuarios registrados en memoria.
* Mensaje de error si las credenciales son incorrectas.
* Navegación a registro si el usuario no tiene cuenta.

---

## 🧭 Navegación

Implementada con **Navigation Compose**:

```kotlin
NavHost(navController, startDestination = "register")
```

Rutas disponibles:

* `register`
* `register_success`
* `login`

---

## 🛠️ Tecnologías utilizadas

* Kotlin
* Android Studio
* Jetpack Compose
* Material 3
* MVVM
* LiveData
* Navigation Compose
* WindowSizeClass

---

## 📸 Capturas

📌 **(Añadir aquí capturas de la app mostrando):**

* Registro en Compact / Medium / Expanded
* Login en distintas orientaciones
* Cambio portrait / landscape

---

## ✅ Requisitos cumplidos

✔ Diseño responsive
✔ Diseño adaptive
✔ MVVM + LiveData
✔ Navegación entre pantallas
✔ Validación completa
✔ Banner con logo y lema
✔ Uso correcto de composables
✔ README documentado

---

## 👤 Autor

Xavier Farrús i Erik Muñoz

**Asignatura:** Desenvolupament d’interfícies
