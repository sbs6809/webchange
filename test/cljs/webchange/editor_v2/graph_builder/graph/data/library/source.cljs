(ns webchange.editor-v2.graph-builder.graph.data.library.source)

(def data {:assets        [{:url "/raw/img/library/main/background.jpg", :size 10, :type "image"}
                           {:url "/raw/img/library/main/Books_Enabled.png", :size 1, :type "image"}
                           {:url "/raw/img/library/main/Easel_Enabled.png", :size 1, :type "image"}
                           {:url "/raw/img/library/main/Hat_Enabled.png", :size 1, :type "image"}
                           {:url "/raw/img/library/main/Tablet_Enabled.png", :size 1, :type "image"}

                           {:url "/raw/audio/l1/a6/L1_A6_Lion_Intro.m4a", :size 2, :type "audio" :alias "librarian books intro" :target "lion"}
                           {:url "/raw/audio/l1/a6/L1_A6_Vera.m4a", :size 2, :type "audio" :alias "vera books intro" :target "vera"}
                           {:url "/raw/audio/background/POL-daily-special-short.mp3", :size 2, :type "audio" :alias "background" :target "background"}],
           :objects       {:background {:type "background", :src "/raw/img/library/main/background.jpg"},
                           :vera       {:type  "animation" :x 725 :y 1000 :name "vera" :anim "idle" :speed 0.3
                                        :width 1800 :height 2558 :scale {:x 0.17 :y 0.17} :start true}

                           :librarian  {:type       "animation" :x 451 :y 883 :name "senoravaca" :anim "idle" :speed 0.3 :skin "lion"
                                        :scene-name "librarian"
                                        :width      351 :height 717 :scale {:x 1 :y 1} :start true}

                           :books      {:type    "image" :x 1292 :y 681 :width 163 :height 106 :src "/raw/img/library/main/Books_Enabled.png"
                                        :actions {:click {:type "action", :id "start-book-scene", :on "click"}}}

                           :easel      {:type "image" :x 791 :y 319 :width 287 :height 430 :src "/raw/img/library/main/Easel_Enabled.png"}
                           :hat        {:type "image" :x 1635 :y 535 :width 227 :height 228 :src "/raw/img/library/main/Hat_Enabled.png"}
                           :tablet     {:type    "image" :x 926 :y 657 :width 224 :height 155 :src "/raw/img/library/main/Tablet_Enabled.png"
                                        :actions {:click {:type "scene", :scene-id "painting-tablet", :on "click"}}}},
           :scene-objects [["background" "books" "easel" "hat" "tablet"] ["librarian" "vera"]],
           :actions       {:start-book-scene       {:type "sequence",
                                                    :data ["welcome"
                                                           "vera-agree"
                                                           "start-reading"
                                                           "go-to-book-scene"]}
                           :welcome                {:type               "animation-sequence",
                                                    :target             "librarian",
                                                    :track              1,
                                                    :offset             2.249,
                                                    :audio              "/raw/audio/l1/a6/L1_A6_Lion_Intro.m4a",
                                                    :start              2.249,
                                                    :duration           4.367,
                                                    :data               [{:start 2.348, :end 4.417, :duration 2.069, :anim "talk"}
                                                                         {:start 5.069, :end 6.513, :duration 1.444, :anim "talk"}]
                                                    :phrase             :welcome
                                                    :phrase-description "Welcome speech"
                                                    :phrase-text        "Welcome to our library! Would you like to read a book?"}
                           :vera-agree             {:type               "animation-sequence",
                                                    :target             "vera",
                                                    :track              1,
                                                    :offset             0.465,
                                                    :audio              "/raw/audio/l1/a6/L1_A6_Vera.m4a",
                                                    :start              0.465,
                                                    :duration           4.184,
                                                    :data               [{:start 0.504, :end 2.073, :duration 1.569, :anim "talk"}
                                                                         {:start 2.611, :end 4.562, :duration 1.951, :anim "talk"}]
                                                    :phrase             :vera-agree
                                                    :phrase-description "Vera agree"
                                                    :phrase-text        "Oh, yes please! I like books a lot."}
                           :start-reading          {:type               "animation-sequence",
                                                    :target             "librarian",
                                                    :track              1,
                                                    :offset             9.249,
                                                    :audio              "/raw/audio/l1/a6/L1_A6_Lion_Intro.m4a",
                                                    :start              9.249,
                                                    :duration           5.407,
                                                    :data               [{:start 9.295, :end 10.457, :duration 1.162, :anim "talk"}
                                                                         {:start 11.016, :end 12.647, :duration 1.631, :anim "talk"}
                                                                         {:start 13.467, :end 14.601, :duration 1.134, :anim "talk"}]
                                                    :phrase             :start-reading
                                                    :phrase-description "Read book"
                                                    :phrase-text        "You are in the right place if you like books. We have hundreds!"}
                           :go-to-book-scene       {:type "scene", :scene-id "book"}
                           :start-background-music {:type "audio" :id "background" :loop true}},
           :audio         {:background "/raw/audio/background/POL-daily-special-short.mp3"}
           :triggers      {:music {:on "start" :action "start-background-music"}}
           :metadata      {:autostart true
                           :prev      "map"}}
  )
