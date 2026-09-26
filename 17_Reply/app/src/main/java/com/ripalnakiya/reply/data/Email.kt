package com.ripalnakiya.reply.data

import androidx.annotation.StringRes

data class Email(
    val id: Long,

    val sender: Account,

    val recipients: List<Account> = emptyList(),

    @field:StringRes val subject: Int = -1,

    @field:StringRes val body: Int = -1,

    var mailbox: MailboxType = MailboxType.Inbox,

    /**
     * Relative duration in which it was created. (e.g. 20 mins ago)
     * It should be calculated from relative time in the future.
     * For now it's hard coded to a [String] value.
     */
    var createdAt: Int = -1
)