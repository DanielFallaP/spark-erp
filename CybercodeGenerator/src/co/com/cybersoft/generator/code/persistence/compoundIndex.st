@CompoundIndexes({
    @CompoundIndex(name = "$tableName$Compound", def = "{$fields$}", unique=true)
})