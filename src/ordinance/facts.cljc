(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Santiago (Comuna de
  Santiago, Chile) -- the SEVENTEENTH municipality-level entry (see
  cloud-itonami-municipality-jpn-tokyo, -usa-washington-dc, -gbr-london,
  -can-toronto, -deu-berlin, -fra-paris, -nld-amsterdam, -esp-madrid,
  -kor-seoul, -ita-roma, -aus-sydney, -arg-buenos-aires, -fin-helsinki,
  -dnk-copenhagen, -nor-oslo, -bel-brussels for the first sixteen) per
  ADR-2607141700 (cloud-itonami-compliance-fact-federation).

  transparencia.munistgo.cl's ordinance PDF hit a TLS certificate error
  ('unable to verify the first certificate') -- a new failure class for
  this family, distinct from HTTP 403/404/PDF-garbling/ECONNREFUSED
  seen elsewhere. Every entry here instead cites the Municipality's own
  OFFICIAL documentos.munistgo.cl 'Decretos y Ordenanzas' listing page
  -- never fabricated. An ordinance not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"santiago"
   [{:ordinance/id "santiago.reglamento-971-transparencia-activa"
     :ordinance/title "Reglamento N°971-2025 (Normas de Transparencia Activa y Acceso a la Información Pública)"
     :ordinance/municipality "santiago"
     :ordinance/country "CHL"
     :ordinance/kind :ordinance
     :ordinance/number "Reglamento N°971-2025"
     :ordinance/url "https://documentos.munistgo.cl/category/decretos-y-ordenanzas/"
     :ordinance/url-provenance :official-munistgo-cl
     :ordinance/enacted-date "2025-12-19"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:transparency :information-disclosure}}
    {:ordinance/id "santiago.ordenanza-130-tendidos-en-desuso"
     :ordinance/title "Ordenanza N°130 (Ordenamiento y Retiro de Tendidos Aéreos y Subterráneos en Desuso)"
     :ordinance/municipality "santiago"
     :ordinance/country "CHL"
     :ordinance/kind :ordinance
     :ordinance/number "Ordenanza N°130 de 2026"
     :ordinance/url "https://documentos.munistgo.cl/category/decretos-y-ordenanzas/"
     :ordinance/url-provenance :official-munistgo-cl
     :ordinance/enacted-date "2026-05-13"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:infrastructure :public-order}}]})

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
      :note (str "cloud-itonami-municipality-chl-santiago Wave 0 (ADR-2607141700): "
                 (count (get catalog "santiago")) " Santiago entries seeded "
                 "with an official munistgo.cl citation. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
