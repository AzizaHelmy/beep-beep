package org.thechance.service_identity.domain.usecases.validation

import org.koin.core.annotation.Single
import org.thechance.service_identity.domain.entity.UserInfo
import org.thechance.service_identity.domain.util.RequestValidationException
import org.thechance.service_identity.domain.util.*

interface IUserInfoValidationUseCase {

    fun validateUserInformation(user: UserInfo, password: String?)

    fun validateUpdateUserInformation(fullName: String?, email: String?)

    fun validateUsernameIsNotEmpty(username: String): Boolean

    fun validateFullNameIsNotEmpty(fullName: String): Boolean

    fun validatePasswordIsNotEmpty(password: String): Boolean

    fun validateUsername(username: String): Boolean

    fun validatePasswordLength(password: String): Boolean

    fun validateEmail(email: String): Boolean
}

@Single
class UserInfoValidationUseCase : IUserInfoValidationUseCase {

    override fun validateUserInformation(user: UserInfo, password: String?) {
        val reasons = mutableListOf<String>()

        if (!validateUsernameIsNotEmpty(user.username)) {
            reasons.add(USERNAME_CANNOT_BE_BLANK)
        }

        if (!validateUsername(user.username)) {
            reasons.add(INVALID_USERNAME)
        }

        if (!validateFullNameIsNotEmpty(user.fullName)) {
            reasons.add(INVALID_FULLNAME)
        }

        if (password == null) {
            reasons.add(INVALID_REQUEST_PARAMETER)

        }

        password?.let {
            if (!validatePasswordIsNotEmpty(password)) {
                reasons.add(PASSWORD_CANNOT_BE_BLANK)
            }
            if (!validatePasswordLength(password)) {
                reasons.add(PASSWORD_CANNOT_BE_LESS_THAN_8_CHARACTERS)
            }
        }

        if (!validateEmail(user.email)) {
            reasons.add(INVALID_EMAIL)
        }

        if (reasons.isNotEmpty()) {
            throw RequestValidationException(reasons)
        }
    }

    override fun validateUpdateUserInformation(fullName: String?, email: String?) {
        val reasons = mutableListOf<String>()

        fullName?.let {
            if (!validateFullNameIsNotEmpty(it)) {
                reasons.add(INVALID_FULLNAME)
            }
        }

        email?.let {
            if (!validateEmail(it)) {
                reasons.add(INVALID_EMAIL)
            }
        }

        if (reasons.isNotEmpty()) {
            throw RequestValidationException(reasons)
        }
    }

    override fun validateUsernameIsNotEmpty(username: String): Boolean = username.isNotBlank()

    override fun validateUsername(username: String): Boolean {
        val validUserNameRegex = "[a-zA-Z0-9_]+".toRegex()
        return username.matches(validUserNameRegex)
    }

    override fun validateFullNameIsNotEmpty(fullName: String): Boolean = fullName.isNotBlank()

    override fun validatePasswordIsNotEmpty(password: String): Boolean = password.isNotBlank()

    override fun validatePasswordLength(password: String): Boolean = password.length > 8

    override fun validateEmail(email: String): Boolean {
        val validEmailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()
        return email.matches(validEmailRegex)
    }

}