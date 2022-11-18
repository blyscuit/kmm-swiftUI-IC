package co.nimblehq.blisskmmic.helpers.json

const val ERROR_JSON_RESULT = """
    {"errors":[{"detail":"The access token is invalid","code":"invalid_token"}]}
"""

const val NOT_FOUND_JSON_RESULT = """
    {"errors":[{"detail":"Not found","code":"not_found"}]}
"""

const val UNAUTHORIZED_JSON_RESULT = """
    {"errors":[{"detail":"Unauthorized","code":"unauthorized"}]}
"""
