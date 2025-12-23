import React, { useState } from "react";
import {
  View,
  TextInput,
  Button,
  FlatList,
  ActivityIndicator,
  Text,
} from "react-native";
import { api } from "../api/client";
import ItemCard from "../components/ItemCard";

export default function SearchScreen() {
  const [query, setQuery] = useState("");
  const [items, setItems] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const searchItems = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await api.get(`/items/search`, {
        params: { name: query },
      });
      setItems(response.data);
    } catch (e: any) {
      console.error(e);
      setError("Failed to load items.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={{ flex: 1, padding: 20, paddingTop: 50 }}>
      <TextInput
        placeholder="Search for an item (e.g., Milk)..."
        value={query}
        onChangeText={setQuery}
        style={{
          borderWidth: 1,
          borderRadius: 10,
          padding: 10,
          marginBottom: 10,
        }}
      />
      <Button title="Search" onPress={searchItems} />
      {loading && <ActivityIndicator style={{ marginTop: 10 }} />}

      {error && (
        <Text style={{ color: "red", marginTop: 10 }}>
          {error}
        </Text>
      )}

      {!loading && !error && items.length === 0 && (
        <Text style={{ marginTop: 10 }}>No items found yet.</Text>
      )}

      <FlatList
        style={{ marginTop: 10 }}
        data={items}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => <ItemCard item={item} />}
      />
    </View>
  );
}
