# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## 2026-01-29
## Add
- UserDTO
- Exposed getAllUsers API. 
- Exposed login API
- JWT Service and Filters
- Security Config
- Secured API
- JWT Secrets and Expiration time
- Implemented UserDetails
- Encrypt password using bCrypt
- Validation - add validation
- Add default role for all new users. 
- Validation Aspect
- GlobalException Handler
- Add role claims
- Exposed Logout API, add blacklist service to invalidate token after logout. 
- Add validation to guard against making requests after logout. 
- Remove Postgres Driver and add H2 Driver to use In-Memory DB

## 2026-01-29
## Change
- Modify UserMapper to be match the response body code from the project spec.


## 2026-01-28
### Added
- UserController *Handles API requests*
- UserRepo
- UserService
- UserMapper
- User - *entity*
- UserResponseDTO
- LoginResponseDTO
- LogoutResponseDTO
- UserRequestDTO
- LoginRequestDTO

