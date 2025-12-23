import React, { useState } from "react";
import {
  View,
  TextInput,
  Button,
  FlatList,
  Text,
  ActivityIndicator,
  StyleSheet,
} from "react-native";
import { api } from "@/src/api/client";          // uses your client.ts
import ItemCard from "@/src/components/ItemCard"; // uses your ItemCard

export default function SearchScreen() {
  const [query, setQuery] = useState("");
  const [items, setItems] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const searchItems = async () => {
    if (!query.trim()) return;

    setLoading(true);
    setError(null);

    try {
      const response = await api.get("/items/search", {
        params: { name: query },
      });
      setItems(response.data);
    } catch (e) {
      console.error(e);
      setError("Failed to load items. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>TownFind Search</Text>

      <TextInput
        placeholder="Search for an item (e.g., Milk)..."
        value={query}
        onChangeText={setQuery}
        style={styles.input}
      />

      <Button title="Search" onPress={searchItems} />

      {loading && <ActivityIndicator style={{ marginTop: 10 }} />}

      {error && (
        <Text style={styles.error}>
          {error}
        </Text>
      )}

      {!loading && !error && items.length === 0 && (
        <Text style={styles.empty}>No items found yet.</Text>
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

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    paddingTop: 50,
    backgroundColor: "#f5f5f5",
  },
  title: {
    fontSize: 22,
    fontWeight: "bold",
    marginBottom: 10,
  },
  input: {
    borderWidth: 1,
    borderRadius: 10,
    padding: 10,
    marginBottom: 10,
    backgroundColor: "#fff",
  },
  error: {
    color: "red",
    marginTop: 10,
  },
  empty: {
    marginTop: 10,
    color: "#555",
  },
});
