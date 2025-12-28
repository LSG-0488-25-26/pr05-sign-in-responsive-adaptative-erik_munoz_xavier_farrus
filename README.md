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

<img width="1017" height="638" alt="image" src="https://github.com/user-attachments/assets/4c60936a-05d0-41c2-ac12-5d2b68a51379" />
<img width="1013" height="630" alt="image" src="https://github.com/user-attachments/assets/8e8dea53-583c-45c2-b55c-64c9016da8d7" />
<img width="1011" height="633" alt="image" src="https://github.com/user-attachments/assets/88379884-c479-4a14-93c6-7894363cba69" />
<img width="998" height="624" alt="image" src="https://github.com/user-attachments/assets/73e63957-2c11-4711-aa40-2c975760831e" />
<img width="1016" height="632" alt="image" src="https://github.com/user-attachments/assets/f1654a3a-0c46-4e39-b8bf-aa8bb9f02195" />
<img width="1026" height="637" alt="image" src="https://github.com/user-attachments/assets/89816e3a-0e36-4ddd-90bd-b569de665910" />
<img width="283" height="638" alt="image" src="https://github.com/user-attachments/assets/38045f40-f39b-4f10-a0f0-4475e596ee9f" />
<img width="285" height="639" alt="image" src="https://github.com/user-attachments/assets/74f3b616-3c33-4dd3-85cc-d8201d2a1be1" />
<img width="288" height="645" alt="image" src="https://github.com/user-attachments/assets/6552c706-2be5-4a7d-a2a1-9b2cf84501e1" />
<img width="1259" height="555" alt="image" src="https://github.com/user-attachments/assets/4d770452-e361-4fa3-ac70-62f368c4fdc3" />
<img width="1260" height="551" alt="image" src="https://github.com/user-attachments/assets/35996f06-b8cb-40e8-af43-5ad371574ee3" />
<img width="1261" height="563" alt="image" src="https://github.com/user-attachments/assets/a2a6c6fc-b34c-487b-a3c4-4d15aad95055" />
<img width="940" height="413" alt="image" src="https://github.com/user-attachments/assets/893c589f-3d28-44a6-be7a-6952bdd82a21" />
<img width="925" height="419" alt="image" src="https://github.com/user-attachments/assets/1efdb96f-efb5-473c-aa15-81498b29dac0" />

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
