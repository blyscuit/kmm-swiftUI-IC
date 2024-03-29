package co.nimblehq.blisskmmic.helpers.json

const val SURVEY_DETAIL_JSON_RESULT = """
{
  "data": {
    "id": "d5de6a8f8f5f1cfe51bc",
    "type": "survey",
    "attributes": {
      "title": "Scarlett Bangkok",
      "description": "We'd love ot hear from you!",
      "thank_email_above_threshold": "<span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"font-size:14px\">Dear {name},<br /><br />Thank you for visiting Scarlett Wine Bar &amp; Restaurant at Pullman Bangkok Hotel G &nbsp;and for taking the time to complete our guest feedback survey!<br /><br />Your feedback is very important to us and each survey is read individually by the management and owners shortly after it is sent. We discuss comments and suggestions at our daily meetings and use them to constantly improve our services.<br /><br />We would very much appreciate it if you could take a few more moments and review us on TripAdvisor regarding your recent visit. By <a href=\"https://www.tripadvisor.com/Restaurant_Review-g293916-d2629404-Reviews-Scarlett_Wine_Bar_Restaurant-Bangkok.html\">clicking here</a> you will be directed to our page.&nbsp;<br /><br />Thank you once again and we look forward to seeing you soon!<br /><br />The Team at Scarlett Wine Bar &amp; Restaurant&nbsp;</span></span><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">Pullman Bangkok Hotel G</span>",
      "thank_email_below_threshold": "<span style=\"font-size:14px\"><span style=\"font-family:arial,helvetica,sans-serif\">Dear {name},<br /><br />Thank you for visiting&nbsp;</span></span><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">Uno Mas at Centara Central World&nbsp;</span><span style=\"font-size:14px\"><span style=\"font-family:arial,helvetica,sans-serif\">&nbsp;and for taking the time to complete our customer&nbsp;feedback survey.</span></span><br /><br /><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">The Team at&nbsp;</span><span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"font-size:14px\">Scarlett Wine Bar &amp; Restaurant&nbsp;</span></span><span style=\"font-family:arial,helvetica,sans-serif; font-size:14px\">Pullman Bangkok Hotel G</span>",
      "is_active": true,
      "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
      "created_at": "2017-01-23T07:48:12.991Z",
      "active_at": "2015-10-08T07:04:00.000Z",
      "inactive_at": null,
      "survey_type": "Restaurant"
    },
    "relationships": {
      "questions": {
        "data": [
          {
            "id": "d3afbcf2b1d60af845dc",
            "type": "question"
          },
          {
            "id": "940d229e4cd87cd1e202",
            "type": "question"
          },
          {
            "id": "ea0555f328b3b0124127",
            "type": "question"
          },
          {
            "id": "16e68f5610ef0e0fa4db",
            "type": "question"
          },
          {
            "id": "bab38ad82eaf22afcdfe",
            "type": "question"
          },
          {
            "id": "85275a0bf28a6f3b1e63",
            "type": "question"
          },
          {
            "id": "642770376f7cd0c87d3c",
            "type": "question"
          },
          {
            "id": "b093a6ad9a6a466fa787",
            "type": "question"
          },
          {
            "id": "e593b2fa2f81891a2b1e",
            "type": "question"
          },
          {
            "id": "c3a9b8ce5c2356010703",
            "type": "question"
          },
          {
            "id": "fbf5d260de1ee6195473",
            "type": "question"
          },
          {
            "id": "4372463ce56db58c0983",
            "type": "question"
          }
        ]
      }
    }
  },
  "included": [
    {
      "id": "d3afbcf2b1d60af845dc",
      "type": "question",
      "attributes": {
        "text": "\nThank you for visiting Scarlett!\n Please take a moment to share your feedback.",
        "help_text": null,
        "display_order": 0,
        "short_text": "introduction",
        "pick": "none",
        "display_type": "intro",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/2001ebbfdcbf6c00c757_",
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
        "cover_image_opacity": 0.6,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": []
        }
      }
    },
    {
      "id": "940d229e4cd87cd1e202",
      "type": "question",
      "attributes": {
        "text": "Food â€“ Variety, Taste and Presentation",
        "help_text": null,
        "display_order": 1,
        "short_text": "Food",
        "pick": "one",
        "display_type": "star",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "4cbc3e5a1c87d99bc7ee",
              "type": "answer"
            },
            {
              "id": "6e6221ce7e0d1068874d",
              "type": "answer"
            },
            {
              "id": "037574cb93d16800eecd",
              "type": "answer"
            },
            {
              "id": "f09f1a680789b636459b",
              "type": "answer"
            },
            {
              "id": "bafadb5f1d8defa4778a",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "ea0555f328b3b0124127",
      "type": "question",
      "attributes": {
        "text": "Beverages â€“ Variety, Taste and Presentation",
        "help_text": null,
        "display_order": 2,
        "short_text": "Quality of beverage",
        "pick": "one",
        "display_type": "star",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "8e1a918e724c89397a73",
              "type": "answer"
            },
            {
              "id": "b31dca3a711cdddc06da",
              "type": "answer"
            },
            {
              "id": "d43e7db9dedf66326795",
              "type": "answer"
            },
            {
              "id": "3f5c5676e40cb185a0e8",
              "type": "answer"
            },
            {
              "id": "bd7f1fdb0cba0f7368ce",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "16e68f5610ef0e0fa4db",
      "type": "question",
      "attributes": {
        "text": "Quality of Service, Speed and Efficiency",
        "help_text": null,
        "display_order": 3,
        "short_text": "Quality of service",
        "pick": "one",
        "display_type": "star",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "dd0a88c4c76391afb125",
              "type": "answer"
            },
            {
              "id": "df3013bf2048eb8624f0",
              "type": "answer"
            },
            {
              "id": "534f04bca9d051c8deb3",
              "type": "answer"
            },
            {
              "id": "0d769dbd5804c8f04030",
              "type": "answer"
            },
            {
              "id": "0adbe83b90f3d8342ded",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "bab38ad82eaf22afcdfe",
      "type": "question",
      "attributes": {
        "text": "Staff- Friendliness and Helpfulness",
        "help_text": null,
        "display_order": 4,
        "short_text": "Staff Friendliness",
        "pick": "one",
        "display_type": "star",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "e62a22a664363eee0087",
              "type": "answer"
            },
            {
              "id": "b34705d5faf62a3bc800",
              "type": "answer"
            },
            {
              "id": "d3b786f5c0b9eca4a863",
              "type": "answer"
            },
            {
              "id": "ae5825c1b2acf846d53a",
              "type": "answer"
            },
            {
              "id": "f50cba5438093e823030",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "85275a0bf28a6f3b1e63",
      "type": "question",
      "attributes": {
        "text": "Restaurant Design and Atmosphere",
        "help_text": null,
        "display_order": 5,
        "short_text": "Ambiance",
        "pick": "one",
        "display_type": "heart",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/cf6002aa29fb33d9d21b_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "c670f00635faa85e3abf",
              "type": "answer"
            },
            {
              "id": "83bf2162ccf3147b453c",
              "type": "answer"
            },
            {
              "id": "f688cf66f84988a495aa",
              "type": "answer"
            },
            {
              "id": "a1079c6457ff9e195314",
              "type": "answer"
            },
            {
              "id": "46c607dbf315a7d1790f",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "642770376f7cd0c87d3c",
      "type": "question",
      "attributes": {
        "text": "Overall Satisfaction",
        "help_text": null,
        "display_order": 6,
        "short_text": "Overall Satisfaction",
        "pick": "one",
        "display_type": "smiley",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "c5e8fc3bca388ec15884",
              "type": "answer"
            },
            {
              "id": "db99f794d4c1c94cf16f",
              "type": "answer"
            },
            {
              "id": "b9e6cf7a06deb810bbc7",
              "type": "answer"
            },
            {
              "id": "a44737168ac0a731f469",
              "type": "answer"
            },
            {
              "id": "8ab9b175d4e906cd9b77",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "b093a6ad9a6a466fa787",
      "type": "question",
      "attributes": {
        "text": "How did you hear about us?",
        "help_text": "(You may select more than one)",
        "display_order": 7,
        "short_text": "How did you hear ",
        "pick": "any",
        "display_type": "choice",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "002d338bc37f2142ad44",
              "type": "answer"
            },
            {
              "id": "bdf97897839888be159d",
              "type": "answer"
            },
            {
              "id": "4ac9af2d4bc4a4dd6315",
              "type": "answer"
            },
            {
              "id": "3fd673185845dce4aa7b",
              "type": "answer"
            },
            {
              "id": "20907898b2c2d68159c8",
              "type": "answer"
            },
            {
              "id": "20c9187b64e9d3c36276",
              "type": "answer"
            },
            {
              "id": "2051cda19e6940173c9f",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "e593b2fa2f81891a2b1e",
      "type": "question",
      "attributes": {
        "text": "How likely is that you would recommend\nScarlett to a friend or colleague?",
        "help_text": "(10 is extremely likely)",
        "display_order": 8,
        "short_text": "NPS",
        "pick": "one",
        "display_type": "nps",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "3ffc938a273e45b27109",
              "type": "answer"
            },
            {
              "id": "d244647d0d831a6d8c3a",
              "type": "answer"
            },
            {
              "id": "0c5cb3cf132245ef7f21",
              "type": "answer"
            },
            {
              "id": "6b52662a7315a9354a99",
              "type": "answer"
            },
            {
              "id": "6697f3c5b435adecdec8",
              "type": "answer"
            },
            {
              "id": "e90a40f6fcf2b9a8e593",
              "type": "answer"
            },
            {
              "id": "eced71bdc77252b9fa87",
              "type": "answer"
            },
            {
              "id": "1ad95bb7e499ee203ff2",
              "type": "answer"
            },
            {
              "id": "54daf66f71cc24161b22",
              "type": "answer"
            },
            {
              "id": "e68ac9b06cbfce652df1",
              "type": "answer"
            },
            {
              "id": "3a0961f5c0ac74d62fd3",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "c3a9b8ce5c2356010703",
      "type": "question",
      "attributes": {
        "text": "Your additional comments are welcomed.",
        "help_text": "(Optional)",
        "display_order": 9,
        "short_text": "Comments",
        "pick": "none",
        "display_type": "textarea",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "2a49e148c5b170aca804",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "fbf5d260de1ee6195473",
      "type": "question",
      "attributes": {
        "text": "Don't miss out on our Exclusive Promotions!",
        "help_text": null,
        "display_order": 10,
        "short_text": "About you ",
        "pick": "none",
        "display_type": "textfield",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": null,
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/b41c84934fa8e4c34269_",
        "cover_image_opacity": 0.75,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": [
            {
              "id": "491d49dd6b8174456acf",
              "type": "answer"
            },
            {
              "id": "6db6dcae1a6c6644d723",
              "type": "answer"
            },
            {
              "id": "575db8c074601994bde3",
              "type": "answer"
            }
          ]
        }
      }
    },
    {
      "id": "4372463ce56db58c0983",
      "type": "question",
      "attributes": {
        "text": "Thank you for taking the time to share your feedback!",
        "help_text": null,
        "display_order": 11,
        "short_text": "thanks_message",
        "pick": "none",
        "display_type": "outro",
        "is_mandatory": false,
        "correct_answer_id": null,
        "facebook_profile": "https://www.facebook.com/UnoMasBangkok",
        "twitter_profile": null,
        "image_url": null,
        "cover_image_url": "https://dhdbhh0jsld0o.cloudfront.net/m/cf6002aa29fb33d9d21b_",
        "cover_image_opacity": 0.6,
        "cover_background_color": null,
        "is_shareable_on_facebook": false,
        "is_shareable_on_twitter": false,
        "font_face": null,
        "font_size": null,
        "tag_list": ""
      },
      "relationships": {
        "answers": {
          "data": []
        }
      }
    },
    {
      "id": "4cbc3e5a1c87d99bc7ee",
      "type": "answer",
      "attributes": {
        "text": "1",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 0,
        "alerts": []
      }
    },
    {
      "id": "6e6221ce7e0d1068874d",
      "type": "answer",
      "attributes": {
        "text": "2",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_2",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 25,
        "alerts": []
      }
    },
    {
      "id": "037574cb93d16800eecd",
      "type": "answer",
      "attributes": {
        "text": "3",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_3",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 50,
        "alerts": []
      }
    },
    {
      "id": "f09f1a680789b636459b",
      "type": "answer",
      "attributes": {
        "text": "4",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_4",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 75,
        "alerts": []
      }
    },
    {
      "id": "bafadb5f1d8defa4778a",
      "type": "answer",
      "attributes": {
        "text": "5",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_5",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 100,
        "alerts": []
      }
    },
    {
      "id": "8e1a918e724c89397a73",
      "type": "answer",
      "attributes": {
        "text": "1",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 0,
        "alerts": []
      }
    },
    {
      "id": "b31dca3a711cdddc06da",
      "type": "answer",
      "attributes": {
        "text": "2",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 25,
        "alerts": []
      }
    },
    {
      "id": "d43e7db9dedf66326795",
      "type": "answer",
      "attributes": {
        "text": "3",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 50,
        "alerts": []
      }
    },
    {
      "id": "3f5c5676e40cb185a0e8",
      "type": "answer",
      "attributes": {
        "text": "4",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 75,
        "alerts": []
      }
    },
    {
      "id": "bd7f1fdb0cba0f7368ce",
      "type": "answer",
      "attributes": {
        "text": "5",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 100,
        "alerts": []
      }
    },
    {
      "id": "dd0a88c4c76391afb125",
      "type": "answer",
      "attributes": {
        "text": "1",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 0,
        "alerts": []
      }
    },
    {
      "id": "df3013bf2048eb8624f0",
      "type": "answer",
      "attributes": {
        "text": "2",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 25,
        "alerts": []
      }
    },
    {
      "id": "534f04bca9d051c8deb3",
      "type": "answer",
      "attributes": {
        "text": "3",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 50,
        "alerts": []
      }
    },
    {
      "id": "0d769dbd5804c8f04030",
      "type": "answer",
      "attributes": {
        "text": "4",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 75,
        "alerts": []
      }
    },
    {
      "id": "0adbe83b90f3d8342ded",
      "type": "answer",
      "attributes": {
        "text": "5",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 100,
        "alerts": []
      }
    },
    {
      "id": "e62a22a664363eee0087",
      "type": "answer",
      "attributes": {
        "text": "1",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 0,
        "alerts": []
      }
    },
    {
      "id": "b34705d5faf62a3bc800",
      "type": "answer",
      "attributes": {
        "text": "2",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_2",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 25,
        "alerts": []
      }
    },
    {
      "id": "d3b786f5c0b9eca4a863",
      "type": "answer",
      "attributes": {
        "text": "3",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_3",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 50,
        "alerts": []
      }
    },
    {
      "id": "ae5825c1b2acf846d53a",
      "type": "answer",
      "attributes": {
        "text": "4",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_4",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 75,
        "alerts": []
      }
    },
    {
      "id": "f50cba5438093e823030",
      "type": "answer",
      "attributes": {
        "text": "5",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_5",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 100,
        "alerts": []
      }
    },
    {
      "id": "c670f00635faa85e3abf",
      "type": "answer",
      "attributes": {
        "text": "1",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 0,
        "alerts": []
      }
    },
    {
      "id": "83bf2162ccf3147b453c",
      "type": "answer",
      "attributes": {
        "text": "2",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 25,
        "alerts": []
      }
    },
    {
      "id": "f688cf66f84988a495aa",
      "type": "answer",
      "attributes": {
        "text": "3",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 50,
        "alerts": []
      }
    },
    {
      "id": "a1079c6457ff9e195314",
      "type": "answer",
      "attributes": {
        "text": "4",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 75,
        "alerts": []
      }
    },
    {
      "id": "46c607dbf315a7d1790f",
      "type": "answer",
      "attributes": {
        "text": "5",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 100,
        "alerts": []
      }
    },
    {
      "id": "c5e8fc3bca388ec15884",
      "type": "answer",
      "attributes": {
        "text": "1",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 0,
        "alerts": []
      }
    },
    {
      "id": "db99f794d4c1c94cf16f",
      "type": "answer",
      "attributes": {
        "text": "2",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 25,
        "alerts": []
      }
    },
    {
      "id": "b9e6cf7a06deb810bbc7",
      "type": "answer",
      "attributes": {
        "text": "3",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 50,
        "alerts": []
      }
    },
    {
      "id": "a44737168ac0a731f469",
      "type": "answer",
      "attributes": {
        "text": "4",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 75,
        "alerts": []
      }
    },
    {
      "id": "8ab9b175d4e906cd9b77",
      "type": "answer",
      "attributes": {
        "text": "5",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 100,
        "alerts": []
      }
    },
    {
      "id": "002d338bc37f2142ad44",
      "type": "answer",
      "attributes": {
        "text": "TripAdvisor",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "bdf97897839888be159d",
      "type": "answer",
      "attributes": {
        "text": "Newspaper/Magazine Story",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_2",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "4ac9af2d4bc4a4dd6315",
      "type": "answer",
      "attributes": {
        "text": "Website",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_3",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "3fd673185845dce4aa7b",
      "type": "answer",
      "attributes": {
        "text": "Social Media",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_4",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "20907898b2c2d68159c8",
      "type": "answer",
      "attributes": {
        "text": "Staying at hotel",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_5",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "20c9187b64e9d3c36276",
      "type": "answer",
      "attributes": {
        "text": "Walking by",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_7",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 5,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "2051cda19e6940173c9f",
      "type": "answer",
      "attributes": {
        "text": "Other",
        "help_text": null,
        "input_mask_placeholder": "please specify",
        "short_text": "answer_7",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": true,
        "weight": null,
        "display_order": 6,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "string",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "3ffc938a273e45b27109",
      "type": "answer",
      "attributes": {
        "text": "0",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 0,
        "alerts": []
      }
    },
    {
      "id": "d244647d0d831a6d8c3a",
      "type": "answer",
      "attributes": {
        "text": "1",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_2",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 1,
        "alerts": []
      }
    },
    {
      "id": "0c5cb3cf132245ef7f21",
      "type": "answer",
      "attributes": {
        "text": "2",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_3",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 2,
        "alerts": []
      }
    },
    {
      "id": "6b52662a7315a9354a99",
      "type": "answer",
      "attributes": {
        "text": "3",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_4",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 3,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 3,
        "alerts": []
      }
    },
    {
      "id": "6697f3c5b435adecdec8",
      "type": "answer",
      "attributes": {
        "text": "4",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_5",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 4,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 4,
        "alerts": []
      }
    },
    {
      "id": "e90a40f6fcf2b9a8e593",
      "type": "answer",
      "attributes": {
        "text": "5",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_6",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 5,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 5,
        "alerts": []
      }
    },
    {
      "id": "eced71bdc77252b9fa87",
      "type": "answer",
      "attributes": {
        "text": "6",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_7",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 6,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 6,
        "alerts": []
      }
    },
    {
      "id": "1ad95bb7e499ee203ff2",
      "type": "answer",
      "attributes": {
        "text": "7",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_8",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 7,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 7,
        "alerts": []
      }
    },
    {
      "id": "54daf66f71cc24161b22",
      "type": "answer",
      "attributes": {
        "text": "8",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_9",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 8,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 8,
        "alerts": []
      }
    },
    {
      "id": "e68ac9b06cbfce652df1",
      "type": "answer",
      "attributes": {
        "text": "9",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_10",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 9,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 9,
        "alerts": []
      }
    },
    {
      "id": "3a0961f5c0ac74d62fd3",
      "type": "answer",
      "attributes": {
        "text": "10",
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_11",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 10,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "answer",
        "reference_identifier": null,
        "score": 10,
        "alerts": []
      }
    },
    {
      "id": "2a49e148c5b170aca804",
      "type": "answer",
      "attributes": {
        "text": null,
        "help_text": null,
        "input_mask_placeholder": null,
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "text",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "491d49dd6b8174456acf",
      "type": "answer",
      "attributes": {
        "text": "First Name",
        "help_text": null,
        "input_mask_placeholder": "John",
        "short_text": "answer_1",
        "is_mandatory": false,
        "is_customer_first_name": true,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 0,
        "display_type": "default",
        "input_mask": null,
        "date_constraint": null,
        "default_value": null,
        "response_class": "string",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "6db6dcae1a6c6644d723",
      "type": "answer",
      "attributes": {
        "text": "Mobile No.",
        "help_text": null,
        "input_mask_placeholder": "0999999999",
        "short_text": "answer_5",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": false,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 1,
        "display_type": "default",
        "input_mask": "\\+?[0-9]{6,13}",
        "date_constraint": null,
        "default_value": null,
        "response_class": "string",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    },
    {
      "id": "575db8c074601994bde3",
      "type": "answer",
      "attributes": {
        "text": "Email ",
        "help_text": null,
        "input_mask_placeholder": "you@example.com",
        "short_text": "answer_6",
        "is_mandatory": false,
        "is_customer_first_name": false,
        "is_customer_last_name": false,
        "is_customer_title": false,
        "is_customer_email": true,
        "prompt_custom_answer": false,
        "weight": null,
        "display_order": 2,
        "display_type": "default",
        "input_mask": "[\\.\\-_\\+a-zA-Z0-9]+@[\\-\\a-zA-Z0-9]+(?:\\.[\\-a-zA-Z0-9]+)+",
        "date_constraint": null,
        "default_value": null,
        "response_class": "string",
        "reference_identifier": null,
        "score": null,
        "alerts": []
      }
    }
  ]
}
"""
