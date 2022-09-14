# frozen_string_literal: true

source "https://rubygems.org"

git_source(:github) {|repo_name| "https://github.com/#{repo_name}" }

# Android
gem 'danger'
gem 'danger-android_lint'
gem 'danger-commit_lint'
gem 'danger-kotlin_detekt'
gem 'danger-shroud'

# iOS
gem "cocoapods"
gem "fastlane"
gem "xcov"
gem "danger-swiftlint"
gem "danger-xcode_summary"
gem 'danger-swiftformat'
gem 'danger-xcov'

plugins_path = File.join(File.dirname(__FILE__), 'fastlane', 'Pluginfile')
eval_gemfile(plugins_path) if File.exist?(plugins_path)
