package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/* =======================
   MODEL
   ======================= */

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

enum class HelpTopic {
    PENGAJUAN,
    PENGADUAN,
    AKUN,
    BANTUAN,
    AGEN
}

enum class ChatStep {
    ASK,
    SOLUTION
}

data class ChatState(
    val topic: HelpTopic? = null,
    val step: ChatStep = ChatStep.ASK
)

/* =======================
   SCREEN
   ======================= */

@Composable
fun PusatBantuanScreen(navController: NavController) {

    var input by remember { mutableStateOf("") }
    val chatList = remember { mutableStateListOf<ChatMessage>() }
    var chatState by remember { mutableStateOf(ChatState()) }

    val listState = rememberLazyListState()

    // auto scroll ke bawah
    LaunchedEffect(chatList.size) {
        if (chatList.isNotEmpty()) {
            listState.animateScrollToItem(chatList.lastIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Pusat Bantuan",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chatList) { chat ->
                ChatBubble(chat)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("Tulis pertanyaan kamu...") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (input.isBlank()) return@IconButton

                    chatList.add(ChatMessage(input, true))

                    if (input.lowercase() == "selesai") {
                        chatList.add(
                            ChatMessage(
                                "Terima kasih 😊 Jika butuh bantuan lain, silakan ketik kembali.",
                                false
                            )
                        )
                        chatState = ChatState()
                        input = ""
                        return@IconButton
                    }

                    val (reply, newState) = smartReply(input, chatState)
                    chatList.add(ChatMessage(reply, false))
                    chatState = newState
                    input = ""
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Kirim")
            }
        }
    }
}

/* =======================
   CHAT BUBBLE
   ======================= */

@Composable
fun ChatBubble(chat: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
        if (chat.isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (chat.isUser)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        ) {
            Text(
                text = chat.text,
                modifier = Modifier
                    .padding(12.dp)
                    .widthIn(max = 260.dp)
            )
        }
    }
}

/* =======================
   SMART REPLY
   ======================= */

fun smartReply(
    input: String,
    state: ChatState
): Pair<String, ChatState> {

    val text = input.lowercase()

    if (state.topic == null) {
        return when {
            text.contains("pengajuan") ->
                Pair(
                    "Baik, saya bantu pengajuan bantuan.\nKendala apa yang dialami?",
                    ChatState(HelpTopic.PENGAJUAN)
                )

            text.contains("pengaduan") || text.contains("lapor") ->
                Pair(
                    "Ini terkait pengaduan.\nPengaduan tentang apa?",
                    ChatState(HelpTopic.PENGADUAN)
                )

            text.contains("akun") || text.contains("login") ->
                Pair(
                    "Masalah akun/login.\nApa kendalanya?",
                    ChatState(HelpTopic.AKUN)
                )

            text.contains("bantuan") ->
                Pair(
                    "Kami bantu informasi bantuan sosial.\nKetik *selesai* jika sudah.",
                    ChatState(HelpTopic.BANTUAN)
                )

            text.contains("agen") ->
                Pair(
                    "Lokasi agen bisa dicek di menu Agen Terdekat.",
                    ChatState(HelpTopic.AGEN)
                )

            else ->
                Pair(
                    "Halo 😊 Saya Pusat Bantuan SlemanCare Plus.\n" +
                            "Ketik: Pengajuan / Pengaduan / Akun / Bantuan / Agen",
                    state
                )
        }
    }

    return when (state.topic) {
        HelpTopic.PENGAJUAN ->
            Pair(
                "Pastikan data sesuai dan dokumen jelas.\nKetik *selesai*.",
                state.copy(step = ChatStep.SOLUTION)
            )

        HelpTopic.PENGADUAN ->
            Pair(
                "Pengaduan diproses maksimal 3x24 jam.\nKetik *selesai*.",
                state.copy(step = ChatStep.SOLUTION)
            )

        HelpTopic.AKUN ->
            Pair(
                "Coba login ulang atau minta OTP ulang.\nKetik *selesai*.",
                state.copy(step = ChatStep.SOLUTION)
            )

        HelpTopic.BANTUAN ->
            Pair(
                "Setiap bantuan memiliki syarat berbeda.\nKetik *selesai*.",
                state.copy(step = ChatStep.SOLUTION)
            )

        HelpTopic.AGEN ->
            Pair(
                "Agen resmi ada di menu Agen Terdekat.\nKetik *selesai*.",
                state.copy(step = ChatStep.SOLUTION)
            )
    }
}
