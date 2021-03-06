(ns webchange.interpreter.renderer.scene.scene
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as r]
    [webchange.interpreter.pixi :refer [Application clear-texture-cache]]
    [webchange.interpreter.renderer.scene.components.create-component :refer [create-component]]
    [webchange.interpreter.renderer.state.scene :as state]
    [webchange.interpreter.renderer.overlays.index :refer [create-overlays update-viewport]]
    [webchange.interpreter.renderer.scene.app :refer [app-exists? get-app register-app get-renderer get-stage]]
    [webchange.interpreter.renderer.scene.components.modes :as modes]
    [webchange.interpreter.renderer.stage-utils :refer [get-stage-params]]))

(defn- set-position
  [stage x y]
  (doto (.-position stage)
    (aset "x" x)
    (aset "y" y)))

(defn- set-scale
  ([stage scale]
   (set-scale stage scale scale))
  ([stage scale-x scale-y]
   (doto (.-scale stage)
     (aset "x" scale-x)
     (aset "y" scale-y))))

(defn- init-app
  [viewport]
  (if (app-exists?)
    (get-app)
    (let [{:keys [x y width height scale-x scale-y]} viewport]
      (doto (Application. (clj->js {:antialias false
                                    :width     width
                                    :height    height}))
        (-> get-stage (set-scale scale-x scale-y))
        (-> get-stage (set-position x y))
        (register-app)))))

(defn- handle-renderer-resize
  [new-width new-height]
  (let [viewport (get-stage-params {:width  new-width
                                    :height new-height})]
    (update-viewport viewport)))

(defn scene
  [{:keys []}]
  (let [container (atom nil)]
    (r/create-class
      {:display-name "web-gl-scene"

       :component-did-mount
                     (fn [this]
                       (re-frame/dispatch [::state/init])
                       (let [{:keys [mode on-ready viewport objects]} (r/props this)
                             app (init-app viewport)]
                         (.appendChild @container (.-view app))
                         (create-component mode {:type        "group"
                                                 :object-name :scene
                                                 :parent      (.-stage app)
                                                 :children    objects})
                         (when (modes/show-overlays? mode)
                           (-> (get-renderer)
                               (.on "resize" handle-renderer-resize))
                           (create-overlays {:parent   (get-stage)
                                             :viewport viewport}))
                         (when (modes/start-on-ready? mode)
                           (on-ready))))

       :component-will-unmount
                     (fn []
                       (-> (get-renderer)
                           (.off "resize" handle-renderer-resize)))

       :should-component-update
                     (fn [] false)

       :reagent-render
                     (fn [{:keys []}]
                       [:div {:style {:width  "100%"
                                      :height "100%"}
                              :ref   #(when % (reset! container %))}])})))
