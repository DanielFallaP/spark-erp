@CompoundIndexes({
    @CompoundIndex(name = "$docName$Compound", def = "{$fields$}", unique=true)
})