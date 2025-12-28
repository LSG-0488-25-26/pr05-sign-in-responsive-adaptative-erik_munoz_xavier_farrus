package com.example.responsiveadaptive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.responsiveadaptive.model.User

class AuthViewModel : ViewModel() {

    // “Base de datos”
    private val users = mutableListOf<User>()

    // Campos
    private val _fullName = MutableLiveData("")
    val fullName: LiveData<String> = _fullName

    private val _birthDate = MutableLiveData("")
    val birthDate: LiveData<String> = _birthDate

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData("")
    val phone: LiveData<String> = _phone

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData("")
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _termsAccepted = MutableLiveData(false)
    val termsAccepted: LiveData<Boolean> = _termsAccepted

    // Errores
    private val _fullNameError = MutableLiveData<String?>(null)
    val fullNameError: LiveData<String?> = _fullNameError

    private val _birthDateError = MutableLiveData<String?>(null)
    val birthDateError: LiveData<String?> = _birthDateError

    private val _emailError = MutableLiveData<String?>(null)
    val emailError: LiveData<String?> = _emailError

    private val _phoneError = MutableLiveData<String?>(null)
    val phoneError: LiveData<String?> = _phoneError

    private val _usernameError = MutableLiveData<String?>(null)
    val usernameError: LiveData<String?> = _usernameError

    private val _passwordError = MutableLiveData<String?>(null)
    val passwordError: LiveData<String?> = _passwordError

    private val _confirmPasswordError = MutableLiveData<String?>(null)
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

    private val _termsError = MutableLiveData<String?>(null)
    val termsError: LiveData<String?> = _termsError

    fun setFullName(v: String) { _fullName.value = v; validateFullName() }
    fun setBirthDate(v: String) { _birthDate.value = v; validateBirthDate() }
    fun setEmail(v: String) { _email.value = v; validateEmail() }
    fun setPhone(v: String) { _phone.value = v; validatePhone() }
    fun setUsername(v: String) { _username.value = v; validateUsername() }
    fun setPassword(v: String) { _password.value = v; validatePassword(); validateConfirmPassword() }
    fun setConfirmPassword(v: String) { _confirmPassword.value = v; validateConfirmPassword() }
    fun setTermsAccepted(v: Boolean) { _termsAccepted.value = v; validateTerms() }


    private fun validateFullName() {
        val v = _fullName.value.orEmpty().trim()
        _fullNameError.value = when {
            v.isEmpty() -> "El nombre es obligatorio"
            !v.matches(Regex("^[A-Za-zÀ-ÿ\\s']{2,}$")) -> "Solo letras y espacios"
            else -> null
        }
    }

    private fun validateBirthDate() {
        val v = _birthDate.value.orEmpty().trim()
        _birthDateError.value = when {
            v.isEmpty() -> "La fecha es obligatoria"
            !v.matches(Regex("^\\d{2}/\\d{2}/\\d{4}$")) -> "Formato: dd/mm/yyyy"
            else -> null
        }
    }

    private fun validateEmail() {
        val v = _email.value.orEmpty().trim()
        _emailError.value = when {
            v.isEmpty() -> "El email es obligatorio"
            !v.contains("@") -> "Debe contener @"
            else -> null
        }
    }

    private fun validatePhone() {
        val v = _phone.value.orEmpty().trim()
        _phoneError.value = when {
            v.isEmpty() -> "El teléfono es obligatorio"
            !v.matches(Regex("^\\d{9,15}$")) -> "Solo números (9-15 dígitos)"
            else -> null
        }
    }

    private fun validateUsername() {
        val v = _username.value.orEmpty().trim()
        _usernameError.value = when {
            v.isEmpty() -> "Usuario obligatorio"
            v.length < 3 -> "Mínimo 3 caracteres"
            users.any { it.username.equals(v, ignoreCase = true) } -> "Ese usuario ya existe"
            else -> null
        }
    }

    private fun validatePassword() {
        val v = _password.value.orEmpty()
        _passwordError.value = when {
            v.isEmpty() -> "Password obligatoria"
            v.length < 6 -> "Mínimo 6 caracteres"
            else -> null
        }
    }

    private fun validateConfirmPassword() {
        val p = _password.value.orEmpty()
        val c = _confirmPassword.value.orEmpty()
        _confirmPasswordError.value = when {
            c.isEmpty() -> "Confirma la password"
            c != p -> "No coincide"
            else -> null
        }
    }

    private fun validateTerms() {
        _termsError.value = if (_termsAccepted.value != true) "Debes aceptar los términos" else null
    }

    private fun validateAll() {
        validateFullName()
        validateBirthDate()
        validateEmail()
        validatePhone()
        validateUsername()
        validatePassword()
        validateConfirmPassword()
        validateTerms()
    }

    fun isFormValid(): Boolean {
        validateAll()
        return listOf(
            _fullNameError.value,
            _birthDateError.value,
            _emailError.value,
            _phoneError.value,
            _usernameError.value,
            _passwordError.value,
            _confirmPasswordError.value,
            _termsError.value
        ).all { it == null }
    }

    // Registro
    fun register(): Boolean {
        if (!isFormValid()) return false

        val u = _username.value!!.trim()
        val p = _password.value!!.toString()

        users.add(User(username = u, password = p))
        return true
    }

    //Login
    private val _loginUsername = MutableLiveData("")
    val loginUsername: LiveData<String> = _loginUsername

    private val _loginPassword = MutableLiveData("")
    val loginPassword: LiveData<String> = _loginPassword

    private val _loginError = MutableLiveData<String?>(null)
    val loginError: LiveData<String?> = _loginError

    fun setLoginUsername(v: String) { _loginUsername.value = v }
    fun setLoginPassword(v: String) { _loginPassword.value = v }

    fun login(): Boolean {
        val u = _loginUsername.value.orEmpty()
        val p = _loginPassword.value.orEmpty()

        val user = users.find {
            it.username.equals(u, true) && it.password == p
        }

        return if (user == null) {
            _loginError.value = "Credenciales incorrectas"
            _loginSuccess.value = false
            false
        } else {
            _loginError.value = null
            _loginSuccess.value = true
            true
        }
    }

    private val _loginSuccess = MutableLiveData<Boolean?>(null)
    val loginSuccess: LiveData<Boolean?> = _loginSuccess

}