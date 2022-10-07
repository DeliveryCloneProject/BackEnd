package me.delivery.domain.user.model.dto

import java.util.Objects

open class ResponseObject<E> protected constructor(
    var result: E?
) {

    companion object {
        fun <E> of(element: E): ResponseObject<E?>? {
            if (Objects.isNull(element)) return null

            return ResponseObject(element)
        }
    }
}