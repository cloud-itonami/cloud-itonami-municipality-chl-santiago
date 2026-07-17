(ns culture.facts
  "Regional-culture catalog for Santiago (Chile) -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"santiago"
   [{:culture/id "santiago.dish.completo"
     :culture/name "Completo"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :dish
     :culture/summary "Hot dog variation eaten in Chile, originated in Santiago in the 1920s at soda fountains, introduced by Eduardo Bahamondes after traveling to the United States."
     :culture/url "https://en.wikipedia.org/wiki/Completo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.dish.barros-luco"
     :culture/name "Barros Luco"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :dish
     :culture/summary "Hot Chilean sandwich of beef and melted cheese, created in the restaurant of the National Congress of Chile and named after President Ramon Barros Luco (1910-1915), who regularly ordered it."
     :culture/url "https://en.wikipedia.org/wiki/Barros_Luco"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.dish.pastel-de-choclo"
     :culture/name "Pastel de choclo"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :dish
     :culture/summary "South American dish made from sweetcorn (choclo), traditional in Chilean cuisine among others; widely eaten in Santiago."
     :culture/url "https://en.wikipedia.org/wiki/Pastel_de_choclo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.dish.cazuela"
     :culture/name "Cazuela"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :dish
     :culture/summary "Typical dish of Chile made traditionally with chicken or beef, with ingredients prepared separately and combined when serving; rooted in the indigenous Mapuche korru stock tradition."
     :culture/url "https://en.wikipedia.org/wiki/Cazuela"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.beverage.terremoto"
     :culture/name "Terremoto"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :beverage
     :culture/summary "Chilean cocktail of pipeno wine, pineapple ice cream and grenadine that originated in Santiago, popularized by bars such as La Piojera and El Hoyo and named after the 1985 Santiago earthquake."
     :culture/url "https://es.wikipedia.org/wiki/Terremoto_(bebida)"
     :culture/url-provenance :wikipedia-es
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.beverage.mote-con-huesillo"
     :culture/name "Mote con huesillo"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :beverage
     :culture/summary "Traditional non-alcoholic Chilean summer drink of dried peaches, husked wheat, sugar, water and cinnamon, popular throughout Chile's Mediterranean climate region, which includes Santiago."
     :culture/url "https://en.wikipedia.org/wiki/Mote_con_huesillo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.festival.fiestas-patrias"
     :culture/name "Fiestas Patrias"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :festival
     :culture/summary "Chile's national holidays of 18-19 September commemorating the First Governing Body of 1810, celebrated throughout Chile; official ceremonies and the Great Military Parade take place in Santiago."
     :culture/url "https://en.wikipedia.org/wiki/Fiestas_Patrias_(Chile)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.festival.santiago-a-mil"
     :culture/name "Santiago a Mil"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :festival
     :culture/summary "Annual performing-arts festival held each January in Santiago, the largest arts festival in Chile, featuring theatre, dance and music."
     :culture/url "https://en.wikipedia.org/wiki/Santiago_a_Mil"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.heritage.la-moneda-palace"
     :culture/name "La Moneda Palace"
     :culture/name-local "Palacio de La Moneda"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :heritage
     :culture/summary "Seat of the president of the Republic of Chile, occupying an entire block in the civic district of downtown Santiago."
     :culture/url "https://en.wikipedia.org/wiki/La_Moneda_Palace"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "santiago.heritage.san-cristobal-hill"
     :culture/name "San Cristóbal Hill"
     :culture/name-local "Cerro San Cristóbal"
     :culture/municipality "santiago"
     :culture/country "CHL"
     :culture/kind :heritage
     :culture/summary "Hill in northern Santiago rising about 300 m above the rest of the city, the third highest point in Santiago."
     :culture/url "https://en.wikipedia.org/wiki/San_Crist%C3%B3bal_Hill"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-chl-santiago culture catalog "
                 "(ADR-2607171400): " (count (get catalog "santiago"))
                 " Santiago entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
