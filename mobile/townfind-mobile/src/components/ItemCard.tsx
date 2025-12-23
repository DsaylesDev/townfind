import React from "react";
import { View, Text, StyleSheet } from "react-native";

type ItemCardProps = {
  item: {
    id: number;
    name: string;
    brand?: string;
    sizeText?: string;
  };
};

export default function ItemCard({ item }: ItemCardProps) {
  return (
    <View style={styles.card}>
      <Text style={styles.name}>{item.name}</Text>
      {item.brand ? <Text>{item.brand}</Text> : null}
      {item.sizeText ? <Text>{item.sizeText}</Text> : null}
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    padding: 15,
    backgroundColor: "#fff",
    borderRadius: 10,
    marginBottom: 10,
    elevation: 2,
  },
  name: {
    fontWeight: "bold",
    fontSize: 16,
    marginBottom: 4,
  },
});
