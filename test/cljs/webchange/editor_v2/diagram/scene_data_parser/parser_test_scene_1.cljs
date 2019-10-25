(ns webchange.editor-v2.diagram.scene-data-parser.parser-test-scene-1)

(def data {:objects {:box1 {:type       "transparent"
                            :x          505 :y 375
                            :width      772 :height 1032
                            :scale      {:x 0.3 :y 0.3}
                            :origin     {:type "center-center"}
                            :scene-name "box1"
                            :transition "box1"
                            :states     {:default {:type "transparent"}
                                         :visible {:type  "animation" :name "boxes" :anim "come" :skin "qwestion"
                                                   :speed 0.3 :loop false :start true}}
                            :actions    {:click {:type "action" :id "click-on-box1" :on "click"}}}}

           :actions {:senora-vaca-audio-touch-second-box {:type     "animation-sequence",
                                                          :target   "senoravaca",
                                                          :track    1,
                                                          :offset   52.453,
                                                          :audio    "/raw/audio/english/l1/a1/vaca.m4a",
                                                          :data     [{:start 52.6, :end 53.467, :duration 0.867, :anim "talk"}
                                                                     {:start 54.36, :end 56.307, :duration 1.947, :anim "talk"}
                                                                     {:start 56.987, :end 59.173, :duration 2.186, :anim "talk"}],
                                                          :start    52.453,
                                                          :duration 6.987}

                     :set-current-box2                   {:type "parallel"
                                                          :data [{:type "set-variable" :var-name "current-box" :var-value "box2"}
                                                                 {:type "set-variable" :var-name "current-position-x" :var-value 850}]}


                     :click-on-box1                      {:type     "test-var-scalar"
                                                          :var-name "current-box"
                                                          :value    "box1"
                                                          :success  "first-word"
                                                          :fail     "pick-wrong"}

                     :first-word                         {:type       "sequence"
                                                          :data       ["show-first-box-word"
                                                                       "introduce-word"
                                                                       "bye-current-box"
                                                                       "set-current-box2"
                                                                       "senora-vaca-audio-touch-second-box"]
                                                          :unique-tag "box"}

                     :show-first-box-word                {:type "parallel"
                                                          :data [{:type "animation" :target "box1" :id "wood" :loop false}
                                                                 {:type     "set-skin" :target "box1"
                                                                  :from-var [{:var-name "item-1" :action-property "skin" :var-property "skin"}]}
                                                                 {:type "copy-variable" :var-name "current-word" :from "item-1"}
                                                                 {:type "add-animation" :target "box1" :id "idle_fly1" :loop true}]}

                     :bye-current-box                    {:type "sequence-data"
                                                          :data [{:type "parallel"
                                                                  :data [{:type     "animation" :id "jump"
                                                                          :from-var [{:var-name "current-box" :action-property "target"}]}
                                                                         {:type     "transition" :to {:y -100 :duration 2}
                                                                          :from-var [{:var-name "current-box" :action-property "transition-id"}
                                                                                     {:var-name "current-position-x" :action-property "to.x"}]}]}
                                                                 {:type     "state" :id "default"
                                                                  :from-var [{:var-name "current-box" :action-property "target"}]}]}

                     :vaca-this-is-var                   {:type "action" :from-var [{:var-name "current-word" :var-property "home-vaca-this-is-action"}]}

                     :vaca-can-you-say                   {:type     "animation-sequence",
                                                          :target   "senoravaca",
                                                          :track    1,
                                                          :offset   20.28,
                                                          :audio    "/raw/audio/english/l1/a1/vaca.m4a",
                                                          :data     [{:start 20.363, :end 20.98, :duration 0.617, :anim "talk"}],
                                                          :start    20.28,
                                                          :duration 0.813}

                     :vaca-question-var                  {:type "action" :from-var [{:var-name "current-word" :var-property "home-vaca-question-action"}]}

                     :vaca-word-var                      {:type "action" :from-var [{:var-name "current-word" :var-property "home-vaca-word-action"}]}

                     :introduce-word                     {:type "sequence"
                                                          :data ["empty-big"
                                                                 "vaca-this-is-var"
                                                                 "empty-small"
                                                                 "vaca-can-you-say"
                                                                 "vaca-question-var"
                                                                 "empty-small"
                                                                 "vaca-word-var"
                                                                 "empty-big"]}

                     :pick-wrong                         {:type "sequence"
                                                          :data ["audio-wrong"]}

                     :audio-wrong                        {:type "audio" :id "fw-try-again" :start 0.892 :duration 1.869 :offset 0.2}

                     :empty-small                        {:type "empty" :duration 500}
                     :empty-big                          {:type "empty" :duration 1000}}})