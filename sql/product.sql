CREATE TABLE product
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    price       DOUBLE(10, 2) NOT NULL,
    stock       INT            NOT NULL,
    description VARCHAR(1000),
    image_url   VARCHAR(500),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

ALTER TABLE product ADD COLUMN admin_only BOOLEAN DEFAULT FALSE;

-- OR

CREATE TABLE product
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    price       DOUBLE(10, 2) NOT NULL,
    stock       INT            NOT NULL,
    description VARCHAR(1000),
    image_url   VARCHAR(500),
    category_id BIGINT,
    admin_only BOOLEAN DEFAULT FALSE;
    FOREIGN KEY (category_id) REFERENCES category (id)
);


INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES (e'Core Protein Pro 800g',
                                                                                                         479.00, 100, 'Core Protein Pro är framtaget för dig som alltid vill ha det främsta alternativet, även när det kommer till proteinpulver. Core Protein Pro är ett vassleprotein av vassleisolat och -hydrolysat med flera fördelar gentemot traditionellt vassleprotein.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/core_protein_pro_74487_x2.jpg',
                                                                                                         1, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES (e'Core Protein Pro 3kg',
                                                                                                         1499.00, 50, 'Core Protein Pro är framtaget för dig som alltid vill ha det främsta alternativet, även när det kommer till proteinpulver. Core Protein Pro är ett vassleprotein av vassleisolat och -hydrolysat med flera fördelar gentemot traditionellt vassleprotein.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_protein_pro_74488_x4.webp',
                                                                                                         1, true);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES (e'Core Casein 750g',
                                                                                                         259.00, 100, 'Core Casein innehåller mjölkprotein som utsöndras långsamt i kroppen och förser dina muskler med protein över längre tid än vad exempelvis vassleprotein gör. Tack vare att råvaran är såpass ren som den är blir proteininnehållet högt, minst 80 gram/100 gram, beroende på smak.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_casein_68246_x4.webp',
                                                                                                         1, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES (e'Core Gainer 1,6kg',
                                                                                                         289.00, 100, 'Core Gainer är en välbalanserad gainer med högt proteininnehåll och snabba kolhydrater för dig som vill ge dina muskler det allra bästa. Core Gainer har utvecklats särskilt för personer som vill gå upp i vikt eller behöver fylla på med muskelbyggande protein och kolhydrater efter hårda träningspass.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_gainer_59323_x4.webp',
                                                                                                         1, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Gainer 4kg',
                                                                                                         559.00, 50, 'Core Gainer är en välbalanserad gainer med högt proteininnehåll och snabba kolhydrater för dig som vill ge dina muskler det allra bästa. Core Gainer har utvecklats särskilt för personer som vill gå upp i vikt eller behöver fylla på med muskelbyggande protein och kolhydrater efter hårda träningspass.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_gainer_65535_x4.webp',
                                                                                                         1, true);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Whey Delight 900g',
                                                                                                         219.00, 100, 'Säg hej till din nya hjälte i proteinskåpet: Core Whey Delight! Med sin krämiga konsistens och goda smak blir den snabbt en självklar del av din dag, som en shake efter jobbet, ett mellanmål på språng eller som en proteinboost i din smoothie.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_whey_delight_71413_x4.webp',
                                                                                                         1, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell Trippel Magnesium 90kaps',
                                                                                                         189.00, 500, 'Healthwell Trippel Magnesium är ett högdoserat magnesiumtillskott som erbjuder optimal biotillgänglighet för dig som prioriterar effektiva kosttillskott. Varje kapsel kombinerar tre olika källor av magnesium: magnesiummalat, magnesiumbisglycinat och magnesiumtaurat. En kapsel ger 220 mg elementärt magnesium, vilket är den mängd som kroppen effektivt kan absorbera och använda.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_trippel_magnesium_65378_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell Vitamin B12 1000 Metylerad 90kaps',
                                                                                                         155.00, 500, 'Healthwell Vitamin B12 1000 Metylerad är ett högdoserat tillskott i vegetabiliska kapslar, med 1000 mikrogram B12 och 400 mikrogram folsyra per kapsel. Vitamin B12 är viktigt för flera kroppsliga funktioner och bidrar bland annat till ämnesomsättningen.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_vitamin_b12_1000_metylerad_65419_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell Inositol 1000 120kaps',
                                                                                                         269.00, 250, 'Healthwell Inositol 1000 från Healthwell är ett högdoserat och vegetabiliskt kosttillskott som innehåller 1000 mg inositol per daglig dos. Kapslarna innehåller myo-inositol, den huvudsakliga formen av inositol i kroppen. Rekommenderad dos är 2 kapslar två gånger dagligen före måltid. Inositol har många påstådda hälsofördelar och har kopplats samman med allt från fertilitet och stämningsförbättring till viktkontroll.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_inositol_1000_65299_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell Zink 25 Plus 90kaps',
                                                                                                         159.00, 500,
                                                                                                         'Healthwell Zink 25 Plus är vegetabiliska kapslar som innehåller organiskt zink från tre högvärdiga källor. Detta extra högdoserade zinktillskott är berikat med andra viktiga mineraler för att förhindra att kroppens nivåer av dessa urlakas. Zink spelar en nyckelroll i många kroppsfunktioner, inklusive omsättningen av protein, fett och kolhydrater.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_zink_25_plus_65084_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell Järn, Folsyra, B12 90kaps',
                                                                                                         159.00, 500, 'Healthwell Järn, Folsyra, B12 är ett kombinerat tillskott som innehåller intressanta näringsämnen såsom järn, folsyra och vitamin B12 för blodbildning och koncentrationsförmåga. Kapslar innehåller också vitamin C, som förbättrar kroppens förmåga att absorbera järn. Järnet är i form av järnbisglycinat, vilket är extra skonsamt mot magen.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_jarn_folsyra_b12_65462_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell MultiVitaminer 90kaps',
                                                                                                         209.00, 500, 'Healthwells MultiVitaminer är ett komplett vitamintillskott med samtliga vitaminer, samt utvalda vitaminliknande ämnen såsom PABA, inositol och Q10. Dessutom har ett bioflavonoidkomplex tillsats för att ge dig något alldeles extra.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_multivitaminer_65348_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell Multivitamin Vegan 90kaps',
                                                                                                         239.00, 500, 'Är du vegan och funderar på om du får i dig alla de nödvändiga näringsämnena? Då är Healthwell Multivitamin Vegan perfekt för dig! Med ett helt veganskt innehåll som är särskilt anpassad för dig med vegetarisk och vegansk kost är denna multivitamin ett utmärkt komplement till kosten. I tillskottet hittar du en rad vitaminer, bland annat vitamin A, C, D3, E, B12, B5 och B9 samt mineraler som järn och magnesium.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_multivitamin_vegan_74183_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Healthwell NAD 60kaps',
                                                                                                         329.00, 750, 'Healthwell NAD är ett kosttillskott med Nikotinamid Adenin Dinukleotid i smidiga kapslar. NAD, en form av vitamin B3, är en naturligt förekommande molekyl i våra celler som spelar en viktig roll i kroppens oxidations- och reparationsprocesser.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/healthwell_nad_68680_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Diet Shake (Cappuccino) 420g',
                                                                                                         289.00, 250, 'Diet Shake är ett måltidskomplement som sätter en ny standard inom kosttillskott för bantning och viktkontroll. Diet Shake gör inte bara att du går ner i vikt genom att begränsa ditt kaloriintag utan smakar även fantastiskt bra. Genom att byta ut en måltid per dag med Diet Shake, får du i dig betydligt mindre kalorier i jämförelse med en vanlig måltid, knappt 100 kcal per portion i jämförelse med 400-600 kcal. Det höga protein- och fiberinnehållet medför även en hög mättnadskänsla, så att du håller dig mätt länge på din shake.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/diet_shake_67034_x4.webp',
                                                                                                         3, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Diet Shake (Choklad) 420g',
                                                                                                         289.00, 250, 'Diet Shake är ett måltidskomplement som sätter en ny standard inom kosttillskott för bantning och viktkontroll. Diet Shake gör inte bara att du går ner i vikt genom att begränsa ditt kaloriintag utan smakar även fantastiskt bra. Genom att byta ut en måltid per dag med Diet Shake, får du i dig betydligt mindre kalorier i jämförelse med en vanlig måltid, knappt 100 kcal per portion i jämförelse med 400-600 kcal. Det höga protein- och fiberinnehållet medför även en hög mättnadskänsla, så att du håller dig mätt länge på din shake.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/diet_shake_65757_x4.webp',
                                                                                                         3, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Diet Shake (Jordgubb) 420g',
                                                                                                         289.00, 250, 'Diet Shake är ett måltidskomplement som sätter en ny standard inom kosttillskott för bantning och viktkontroll. Diet Shake gör inte bara att du går ner i vikt genom att begränsa ditt kaloriintag utan smakar även fantastiskt bra. Genom att byta ut en måltid per dag med Diet Shake, får du i dig betydligt mindre kalorier i jämförelse med en vanlig måltid, knappt 100 kcal per portion i jämförelse med 400-600 kcal. Det höga protein- och fiberinnehållet medför även en hög mättnadskänsla, så att du håller dig mätt länge på din shake.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/diet_shake_72452_x4.webp',
                                                                                                         3, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Diet Shake (Skogsbär Yoghurt) 420g',
                                                                                                         289.00, 250, 'Diet Shake är ett måltidskomplement som sätter en ny standard inom kosttillskott för bantning och viktkontroll. Diet Shake gör inte bara att du går ner i vikt genom att begränsa ditt kaloriintag utan smakar även fantastiskt bra. Genom att byta ut en måltid per dag med Diet Shake, får du i dig betydligt mindre kalorier i jämförelse med en vanlig måltid, knappt 100 kcal per portion i jämförelse med 400-600 kcal. Det höga protein- och fiberinnehållet medför även en hög mättnadskänsla, så att du håller dig mätt länge på din shake.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/diet_shake_65543_x4.webp',
                                                                                                         3, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Diet Shake (Hallon) 420g',
                                                                                                         289.00, 250, 'Diet Shake är ett måltidskomplement som sätter en ny standard inom kosttillskott för bantning och viktkontroll. Diet Shake gör inte bara att du går ner i vikt genom att begränsa ditt kaloriintag utan smakar även fantastiskt bra. Genom att byta ut en måltid per dag med Diet Shake, får du i dig betydligt mindre kalorier i jämförelse med en vanlig måltid, knappt 100 kcal per portion i jämförelse med 400-600 kcal. Det höga protein- och fiberinnehållet medför även en hög mättnadskänsla, så att du håller dig mätt länge på din shake.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/diet_shake_71555_x4.webp',
                                                                                                         3, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Diet Shake (Blåbär & vanilj) 420g',
                                                                                                         289.00, 250, 'Diet Shake är ett måltidskomplement som sätter en ny standard inom kosttillskott för bantning och viktkontroll. Diet Shake gör inte bara att du går ner i vikt genom att begränsa ditt kaloriintag utan smakar även fantastiskt bra. Genom att byta ut en måltid per dag med Diet Shake, får du i dig betydligt mindre kalorier i jämförelse med en vanlig måltid, knappt 100 kcal per portion i jämförelse med 400-600 kcal. Det höga protein- och fiberinnehållet medför även en hög mättnadskänsla, så att du håller dig mätt länge på din shake.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/diet_shake_65544_x4.webp',
                                                                                                         3, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Diet Shake (Päron & vanilj) 420g',
                                                                                                         289.00, 250, 'Diet Shake är ett måltidskomplement som sätter en ny standard inom kosttillskott för bantning och viktkontroll. Diet Shake gör inte bara att du går ner i vikt genom att begränsa ditt kaloriintag utan smakar även fantastiskt bra. Genom att byta ut en måltid per dag med Diet Shake, får du i dig betydligt mindre kalorier i jämförelse med en vanlig måltid, knappt 100 kcal per portion i jämförelse med 400-600 kcal. Det höga protein- och fiberinnehållet medför även en hög mättnadskänsla, så att du håller dig mätt länge på din shake.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/diet_shake_66100_x4.webp',
                                                                                                         3, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Complete Meal (Jordgubb) 650g',
                                                                                                         209.00, 200, 'När du är på språng, inte har tid att fixa ett lagat mål mat eller när som helst när vardagen kräver finns Complete Meal - ett måltidspulver som enkelt blandas till en komplett och fullvärdig måltid. Strunta i snabbmat och näringsfattiga alternativ som bara ger tomma kalorier och en tommare plånbok. Complete Meal innehåller långsamma kolhydrater och fibrer från havre som mättar bra och ger långvarig energi, tillsammans med fullvärdigt protein från vassle och hälsosamt fett från kokosolja. Dessutom är vitaminer, mineraler och matsmältningsenzymer tillsatta.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/complete_meal_65583_x4.webp',
                                                                                                         4, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Complete Meal (Vanilj) 650g',
                                                                                                         209.00, 200, 'När du är på språng, inte har tid att fixa ett lagat mål mat eller när som helst när vardagen kräver finns Complete Meal - ett måltidspulver som enkelt blandas till en komplett och fullvärdig måltid. Strunta i snabbmat och näringsfattiga alternativ som bara ger tomma kalorier och en tommare plånbok. Complete Meal innehåller långsamma kolhydrater och fibrer från havre som mättar bra och ger långvarig energi, tillsammans med fullvärdigt protein från vassle och hälsosamt fett från kokosolja. Dessutom är vitaminer, mineraler och matsmältningsenzymer tillsatta.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/complete_meal_65585_x4.webp',
                                                                                                         4, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Protein Pancakes American Style 500g',
                                                                                                         145.00, 150, 'Core Protein Pancakes American Style är en smidig mix för att göra läckra amerikanska pannkakor – perfekt som mellanmål, en lyxig frukost eller snabb lunch. Pannkakorna är varken smaksatta eller sötade, för att du ska kunna smaksätta efter din egen smak. Njut av proteinrika pannkakor som gör musklerna glada med sitt innehåll!',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_protein_pancakes_american_style_66009_x4.webp',
                                                                                                         4, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Protein Pancakes 500g',
                                                                                                         145.00, 150, 'Laga goda och proteinrika pannkakor med Core Protein Pancakes - en pannkaksmix med hela 30 % protein som passar perfekt till en lyxig frukost eller som en snabb lunch. Pannkakorna är osötade och har ingen smaksättning för att du enkelt ska kunna smaksätta själv och kombinera med dina favorittillbehör.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_protein_pancakes_66007_x4.webp',
                                                                                                         4, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('RAW Extreme Gains 4,5kg',
                                                                                                         659.00, 50, 'RAW Extreme Gains är nästa generations massiva gainerformula. Förutom dess täta kaloriinnehåll utmärker sig produkten genom att ge en balans av både snabba och långsamma kolhydrater från havre. Så att du får en jämn tillförsel av kolhydrater och inte blodsockerfall.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/raw_extreme_gains_53777_x4.webp',
                                                                                                         1, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('RAW Extreme Protein Mass 2,1kg',
                                                                                                         479.00, 50, 'RAW Extreme Protein Mass är för dig som inte lämnar något åt slumpen. När du bestämt dig för att bygga extrem muskelmassa behöver din kropp ordentligt, kvalitativt bränsle för att orka hela vägen. RAW Extreme Protein Mass levererar en fullmatad blandning av kvalitativa kolhydrater och protein med både långsamt och snabbt upptag. Kolhydraterna behövs för att orka lyfta tungt, samtidigt som protein ger näring till dina trötta muskler.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/raw_extreme_protein_mass_70191_x4.webp',
                                                                                                         1, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Vitamins Man 120kaps',
                                                                                                         239.00, 250, 'Skippa flera olika kosttillskott att hålla koll på: två kapslar om dagen av Core Vitamins Man räcker för att få i dig en lång rad viktiga vitaminer och mineraler. I denna multikapsel får du bland annat magnesium, det viktiga mineralet för normal muskelavslappning, samt vitamin A, C, D3 och K.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_vitamins_man_65729_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Magnesium Bisglycinate 90kaps',
                                                                                                         159.00, 500, 'Tränar du mycket och letar efter ett högkvalitativt magnesiumtillskott? Core Magnesium Bisglycinate innehåller en biotillgänglig form av magnesium bundet till aminosyran glycin. Magnesium har många funktioner i kroppen, bland annat bidrar det till musklernas avslappning och till att minska trötthet och utmattning. Dessutom är magnesium tillsammans med glycinat en utmärkt kombination för den som vill slappna av på kvällen.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_magnesium_bisglycinate_73118_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Electrolytes 130g',
                                                                                                         169.00, 200, 'Core Electrolytes är produkten för dig som tränar hårt eller långvarigt och vill fylla på förrådet av mineraler efter intensiv svettning. Produkten innehåller mineraler som hjälper till att upprätthålla mineralbalansen, däribland magnesium som bidrar till elektrolytbalansen. Dessutom ingår aminosyran taurin som är vida omtalad för sin funktion i energidrycker.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_electrolytes_65717_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Betakaroten 50 30kaps',
                                                                                                         115.00, 750, 'Core Betakaroten 50 är solskenstillskottet med extra högdoserad och lättupptagbar betakaroten, med naturlig betakaroten från Blakeslea trispora. Dessutom är MCT-olja från kokosnöt och svartpepparextrakt tillsatt för en extra bra upptagningsförmåga. Ladda upp med betakaroten i god tid före solsäsongen för att förbereda dig och huden på en jämn och fräsch solbränna.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_betakaroten_50_65663_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Betakaroten 100 30kaps',
                                                                                                         169.00, 750, 'Core Betakaroten 100 är utvecklad med fokus på alla solälskare som värdesätter härlig solbrun hudnyans som varar längre. Produkten innehåller en extra hög dos betakaroten med 100 mg per kapsel. Dessutom är den förstärkt med piperin för bättre upptag. Rekommenderad dosering är 1 kapsel dagligen - och börja gärna användning minst 10 veckor före solexponering för bästa effekt!',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_betakaroten_100_65630_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Vitamin D3 5000 IE+ 120kaps',
                                                                                                         189.00, 250, 'Core Vitamin D3 5000 IE är små softgelkapslar med vitamin D3 i olja för ett optimalt upptag. Vitamin D3 bidrar bland annat till att reglera kalciumbalansen, till upprätthållandet av skelett och tänder samt till immunsystemets normala funktion. Trots att vi får i oss D-vitamin både genom kosten och via solens strålar, är för låga nivåer av D-vitamin vanligt, inte minst under vinterhalvåret.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_vitamin_d3_5000_ie_69574_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Kalium Complex 120kaps',
                                                                                                         145.00, 250, 'Core Kalium Complex är ett kaliumtillskott med två sorters kalium; kaliumcitrat och kaliummalat - båda med extra bra upptag i kroppen. En portion om 2 kapslar ger hela 1400 milligram kaliumkomplex, varav elementär mängd 500 milligram.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_kalium_complex_65600_x4.webp',
                                                                                                         2, false);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES (e'Core Protein Pro 3kg',
                                                                                                         1499.00, 50, 'Core Protein Pro är framtaget för dig som alltid vill ha det främsta alternativet, även när det kommer till proteinpulver. Core Protein Pro är ett vassleprotein av vassleisolat och -hydrolysat med flera fördelar gentemot traditionellt vassleprotein.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_protein_pro_74488_x4.webp',
                                                                                                         1, true);
INSERT INTO public.product (name, price, stock, description, image_url, category_id, admin_only) VALUES ('Core Gainer 4kg',
                                                                                                         559.00, 50, 'Core Gainer är en välbalanserad gainer med högt proteininnehåll och snabba kolhydrater för dig som vill ge dina muskler det allra bästa. Core Gainer har utvecklats särskilt för personer som vill gå upp i vikt eller behöver fylla på med muskelbyggande protein och kolhydrater efter hårda träningspass.',
                                                                                                         'https://cdn.svensktkosttillskott.se/bilder/webp/core_gainer_65535_x4.webp',
                                                                                                         1, true);