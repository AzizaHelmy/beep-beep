package org.thechance.common.presentation.chat

import org.thechance.common.presentation.base.BaseInteractionListener

interface ChatInteractionListener : BaseInteractionListener {

    fun onClickDropdownMenu()

    fun onDismissDropdownMenu()

    fun onClickLogOut()

}