match :: [Char] -> [Char] -> Bool
match "" _ = True
match _ "" = False
match ('^':regex) text = matchhere regex text
match regex text = matchhere regex text || match regex (tail text)

matchhere :: [Char] -> [Char] -> Bool
matchhere "" _ = True
matchhere (c:'*':regex) text = matchstar c regex text
matchhere "$" text = null text
matchhere ('.':regex) text = matchhere regex $ tail text
matchhere (a:regex) (b:text) = a == b && matchhere regex text
matchhere _ _ = False

matchstar :: Char -> [Char] -> [Char] -> Bool
matchstar _ regex "" = null regex 
matchstar '.' regex text = matchhere regex text || matchstar '.' regex (tail text)
matchstar c regex text = matchhere regex text || c == head text && matchstar c regex (tail text)
