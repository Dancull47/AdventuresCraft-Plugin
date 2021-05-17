package monzter.adventurescraft.plugin.shared.GUIs.shops;

public enum ToolList {
    TOOL("STARTER_PICKAXE", 100)
    ;
    private String id;
    private int price;

    ToolList(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
