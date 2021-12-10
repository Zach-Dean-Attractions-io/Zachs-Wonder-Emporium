package com.example.zachswonderemporium.data.model.utils

import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.data.model.PointOfInterestCategory

object InitialPointsOfInterest {

    fun get(): Array<PointOfInterest> {
        return arrayOf(
            // ATTRACTIONS
            PointOfInterest("1", "Wicker Man",
                "Join the chosen ones on the world’s most immersive rollercoaster experience to date, unifying cutting edge special effects with classic wooden coaster technology for the first time! Be Chosen.",
                52.98958332042, -1.8883829021302,
                "https://www.bordercountiesadvertizer.co.uk/resources/images/7574328.jpg?display=1&htype=0&type=responsive-gallery",
                "https://www.altontowers.com/media/zbsgxja2/wickerman.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("2", "The Smiler",
                "The Smiler is the world’s first 14-loop rollercoaster. Its twisting track combines the world beating 14 loops with a speed of 85 km/h and a track length 3x longer than Oblivion!",
                52.987400348906, -1.89592527388,
                "https://www.altontowers.com/media/ccwj5i5v/01_thesmiler_close.jpg?anchor=center&mode=crop&width=720&height=514",
                "https://www.altontowers.com/media/zftf123n/smiler_logo.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("3", "TH13TEEN",
                "f you go down to the woods today you'd better not go alone... Dip and dive through the Dark Forest, enter the ancient crypt and face the horror that lies within.",
                52.98471346328, -1.8905340337601,
                "https://www.altontowers.com/media/n21faukx/02_th13teen_close.jpg?center=0.2551109732733739,0.69666666666666666&mode=crop&width=1920&height=768&quality=65",
                "https://www.altontowers.com/media/godllmq5/th13teen.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("4", "Galactica",
                "Blast through an advanced launch portal to a dimension beyond your imagination. Soar,  spiral and glide in our iconic flying rollercoaster on an exhilarating expedition.",
                52.985827636856, -1.8824230336991,
                "https://www.altontowers.com/media/14opjq1l/01_galactica_craft__1572864511_195-171-191-66.jpg?anchor=center&mode=crop&width=1400&height=700",
                "https://www.altontowers.com/media/xu1cag5k/galactica.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("5", "Runaway Mine Train",
                "The Runaway Mine Train twists and turns, getting faster and faster.",
                52.989518737048, -1.8856094980088,
                "https://www.altontowers.com/media/fheffzf1/01_minetrain_helix.jpg?anchor=center&mode=crop&width=1400&height=1400",
                "https://www.altontowers.com/media/qttpir0n/runawayminetrain.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("6", "Rita",
                "Brace yourself – as the lights turn green, Rita powers you straight to 100kph in just 2.5 seconds. At lightning pace, Rita navigates the track, as you break free from the clutches of Dark Forest",
                52.984926611752, -1.890877356514,
                "https://www.altontowers.com/media/dfjptmyx/01_rita_closeleft.jpg?center=0.4425,0.22333333333333333&mode=crop&width=1920&height=768&quality=65",
                "https://www.altontowers.com/media/d0lprkaf/rita.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("7", "Octonauts Rollercoaster Adventure",
                "Dive into CBeebies Land and join Captain Barnacles and crew as The Octonauts head to Alton Towers Resort on a Octonauts Rollercoaster Adventure!",
                52.989321757166, -1.8945948982087,
                "https://www.altontowers.com/media/jqyhpxy4/03_octonauts_whale__1572879517_195-171-191-66.jpg?anchor=center&mode=crop&width=1400&height=700",
                "https://www.altontowers.com/media/tcthz1cl/octonauts.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("8", "Oblivion",
                "Oblivion beckons you to face your fears, and as you’re held, overhanging the edge of the world’s first vertical drop rollercoaster, you get a moment to savour what is to come.",
                52.98658009145, -1.8964992666092,
                "https://www.altontowers.com/media/5apjer14/04_oblivion_wide.jpg?center=0.18,0.465&mode=crop&width=1400&height=700",
                "https://www.altontowers.com/media/oqhn0t2g/oblivion.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("9", "Nemesis",
                    "Ride the twists and turns of Nemesis, as it races past the rocks and rivers of Forbidden Valley. Inverted on the track, it corkscrews, spins and races over the twisted steel track.",
                52.986974075322, -1.8828200006333,
                "https://www.altontowers.com/media/cf4dwnhj/nemesis-kv.jpg?center=0.29333333333333333,0.48666666666666669&mode=crop&width=1400&height=700",
                "https://www.altontowers.com/media/xaloebgn/nemesis.png", PointOfInterestCategory.ATTRACTIONS.name),

            PointOfInterest("10", "Duel",
                "When the ghosts and ghouls attack, this is your chance to fight back. Load your laser guns and have your wits about you.\\n\\nA legion of zombies have been drawn to the home of their creator, Dr Nicholas Roodyn, Prepare to Duel!",
                52.988918107061, -1.8837748670426,
                "https://www.altontowers.com/media/s5sbdxfh/01_duel.jpg?center=0.52347603773732876,0.50666666666666671&mode=crop&width=1400&height=700",
                "https://www.altontowers.com/media/ixkbjipn/duel.png", PointOfInterestCategory.ATTRACTIONS.name),

            // SHOWS
            PointOfInterest("11", "Sharkbait Reef",
                "Under the wreckage of the galleon in Mutiny Bay, Captain Black and his crew open up Sharkbait Reef. Brimming with blacktip sharks and chocolate chip starfish, spotted rays, seahorses and a Tropical Interactive Pool.",
                52.989922381542, -1.8942140245285,
                "https://www.altontowers.com/media/auxkcl14/01_sealife_exterior.jpg?center=0.26577258044731572,0.52666666666666662&mode=crop&width=1400&height=700",
                "https://www.altontowers.com/media/l4ig1mjh/sharkbaitreef.png", PointOfInterestCategory.SHOWS.name),

            PointOfInterest("12", "The Alton Towers Dungeon",
                "The Alton Towers Dungeon, a hilariously hideous journey through Staffordshire’s darkest history. Uncover secrets from the past, hidden deep beneath the Towers.  Buy your tickets at www.altontowers.com/traitors",
                52.990645703034, -1.8949650430527,
                "https://www.altontowers.com/media/b1bolyqy/dungeon-judge.jpg?anchor=center&mode=crop&width=1400&height=700",
                "https://www.altontowers.com/media/jmibhcf0/thealtontowersdungeon.png", PointOfInterestCategory.SHOWS.name),

            // FOOD AND DRINK
            PointOfInterest("13", "Rollercoaster Restaurant",
                "Located at Alton Towers Resort the Rollercoaster Restaurant is set below a vast rollercoaster track, where you can watch as orders tackle two gravity defying loop-the-loops before dropping 8 metres - the equivalent of two double decker buses - down the tornado spiral to the table.",
                52.986005256076, -1.882589330658,
                "https://www.altontowers.com/media/ii3b01co/rcr.jpg?anchor=center&mode=crop&width=800&height=800&quality=65",
                "https://www.altontowers.com/media/2iib44zg/atr-logo.png?anchor=center&mode=crop&width=250&height=222", PointOfInterestCategory.FOOD_AND_DRINK.name),

            PointOfInterest("14", "Corner Coffee",
                "Limited seating is available. Guests over the age of 11 are required to wear a suitable face mask within this outlet. If you do not have a mask, \\n\\nVisit Corner Coffee, located on Towers Street, and treat yourself to a cappuccino, latte or something refreshing from the bar. There is also a large selection of sandwiches, baked pastries and delicious cakes for you to indulge into. Corner Coffee is the perfect place to refuel whilst taking a comforting break from our thrilling rides and attractions.",
                52.990080311878, -1.8930281046778,
                "https://www.altontowers.com/media/q3jlbj0y/cornercoffee.jpg?anchor=center&mode=crop&width=800&height=800&quality=65",
                "https://www.altontowers.com/media/2iib44zg/atr-logo.png?anchor=center&mode=crop&width=250&height=222", PointOfInterestCategory.FOOD_AND_DRINK.name),

            PointOfInterest("15", "Explorers Pizza Pasta",
                "Guests over the age of 11 are required to wear a suitable face mask within this outlet. If you do not have a mask, they are available to purchase from our retail outlets.\\n\\nEnter the Explorers restaurant and indulge in a feast of mouth-watering Italian pizza and pasta dishes. Gluten free Pizza's available upon request.\\n\\nMerlin Passholder discount is only available when ordering from the counter as usual, the discount cannot be used with mobile food orders.",
                52.98998989617, -1.8856573943049,
                "https://www.altontowers.com/media/hrejnboj/pizzapasta.jpg?anchor=center&mode=crop&width=800&height=800&quality=65",
                "https://www.altontowers.com/media/2iib44zg/atr-logo.png?anchor=center&mode=crop&width=250&height=222", PointOfInterestCategory.FOOD_AND_DRINK.name)
        )
    }

}