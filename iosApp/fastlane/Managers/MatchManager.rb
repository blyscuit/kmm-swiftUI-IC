# frozen_string_literal: true

class MatchManager
  def initialize(
      fastlane:,
      keychain_name:,
      keychain_password:,
      is_ci:,
      username:
  )
    @fastlane = fastlane
    @keychain_name = keychain_name
    @keychain_password = keychain_password
    @is_ci = is_ci
    @username = username
  end

  def sync_development_signing(app_identifier:, force: false)
    if @is_ci
      create_ci_keychain
      @fastlane.match(
        type: 'development',
        keychain_name: @keychain_name,
        keychain_password: @keychain_password,
        app_identifier: app_identifier,
        readonly: !force,
        force: force
      )
    else
      @fastlane.match(
        type: 'development',
        app_identifier: app_identifier,
        readonly: !force,
        force: force,
        username: @username
      )
    end
  end

  def sync_adhoc_signing(app_identifier:, force: false)
    if @is_ci
      create_ci_keychain
      @fastlane.match(
        type: 'adhoc',
        keychain_name: @keychain_name,
        keychain_password: @keychain_password,
        app_identifier: app_identifier,
        readonly: !force,
        force: force
      )
      @fastlane.update_code_signing_settings(
        use_automatic_signing: false,
        team_id: ENV["sigh_#{app_identifier}_adhoc_team-id"],
        profile_name: ENV["sigh_#{app_identifier}_adhoc_profile-name"],
        code_sign_identity: 'iPhone Distribution'
      )
    else
      @fastlane.match(
        type: 'adhoc',
        app_identifier: app_identifier,
        readonly: !force,
        force: force,
        username: @username
      )
    end
  end

  def sync_app_store_signing(app_identifier:)
    if @is_ci
      create_ci_keychain
      @fastlane.match(
        type: 'appstore',
        keychain_name: @keychain_name,
        keychain_password: @keychain_password,
        app_identifier: app_identifier,
        readonly: true
      )
      @fastlane.update_code_signing_settings(
        use_automatic_signing: false,
        team_id: ENV["sigh_#{app_identifier}_appstore_team-id"],
        profile_name: ENV["sigh_#{app_identifier}_appstore_profile-name"],
        code_sign_identity: 'iPhone Distribution'
      )
    else
      @fastlane.match(
        type: 'appstore',
        app_identifier: app_identifier,
        readonly: true,
        username: @username
      )
    end
  end

  def create_ci_keychain
    @fastlane.create_keychain(
        name: @keychain_name,
        password: @keychain_password,
        default_keychain: true,
        unlock: true,
        timeout: 3600,
        lock_when_sleeps: false
    )
  end
end
